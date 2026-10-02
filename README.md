# product-manager

面向外贸企业的 **产品 + 客户 + 供应商** 一体化管理后台。基于 Spring Boot 2.7 + Thymeleaf 服务端渲染，深度整合作者自研的 **sharp** 系列框架（表单引擎 / 报表引擎 / ORM / Excel / 文件上传）。通过“配置驱动 + Advice 扩展”的方式，用极少的业务代码即可搭建出带增删改查、子表、字典、报表列表、导出的完整后台。

- 版本：`app.version`（见 `application.yml`，当前 `v2026.03.14.3`）
- 作者：Rick.Xu

---

## 一、技术栈

| 层 | 选型 |
| --- | --- |
| 运行时 | Java 8、Spring Boot 2.7.9 |
| Web | Spring MVC、Spring Security（Session 认证、RBAC）、Spring WebSocket（STOMP 实时消息） |
| 模板 | Thymeleaf + 自定义方言处理器（`sp:select` / `sp:file` / `sp:form` 等，见 `config/dialect/processor`） |
| 持久化 | MySQL + Druid 连接池；自研 `sharp-database` ORM（实体注解 `@Table`/`@Column`/`@OneToMany`/`@ManyToOne`/`@Embedded`，逻辑删除 `is_deleted`，审计字段自动填充） |
| 表单引擎 | `sharp-formflow`（`Form` / `FormBO` / `FormAdvice` / `CpnInstanceProcessor`，配置化组件 + 自定义模板） |
| 报表引擎 | `sharp-report`（配置驱动的列表/查询/导出，`ReportAdvice` 扩展） |
| Excel | `sharp-excel` + Apache POI（产品报价单导出，RMB/USD 模板） |
| 文件上传 | `sharp-fileupload` + 阿里云 OSS（`OSSInputStreamStore`） |
| 邮件 | `sharp-mail` + Spring Mail（SMTP 发送 / IMAP 接收） |
| 缓存 | Ehcache 3 + JCache（`@EnableCaching`） |
| 校验 | `spring-boot-starter-validation`（Hibernate Validator） |
| 验证码 | Kaptcha |
| 其它 | itext（PDF）、UserAgentUtils、Spring Mobile |

### 前端
CoreUI + Bootstrap 4、jQuery、多标签页（nthTabs）、Toastr、Multiple-Select、Bootstrap-Datepicker、zTree、CKEditor。无前后端分离，全部为 Thymeleaf 服务端渲染 + 少量 jQuery 脚本。

---

## 二、整体架构

```
com.rick.manager
├── ProductManagerApplication        # 启动类（@EnableCaching，@Import 文件上传组件）
├── config
│   ├── SharpConfig                  # ColumnAutoFill（create_by/update_by 自动填充当前用户）、OSS、Validator
│   ├── MvcConfig                    # 视图控制器（/releases → changeLog 等）
│   ├── ThymeleafConfig              # 注册自定义方言
│   └── dialect/processor           # sp:select / sp:file / sp:form 等标签处理器
├── auth                             # 登录、验证码、UserContextHolder、Security 配置（CSRF 关闭）
├── sys                              # 用户 / 角色 / 权限（RBAC）
├── common                           # BaseFormController、通用 API、异常、工具
├── plugin/ztree                     # 树形组件
└── module
    ├── product                      # 产品、报价导出
    ├── customer                     # 客户、跟进记录、跟进面板（任务）
    ├── supplier                     # 供应商
    ├── code                         # 编号序列生成（C/S 前缀，按日重置）
    ├── message                      # 站内消息 + WebSocket 推送
    ├── ckeditor                     # 富文本上传
    └── common                       # CodeDescription 等共享实体/服务
```

### 关键设计模式

- **配置驱动的表单**：`sys_form` + `sys_form_cpn` 配置每个表单的字段/组件/校验；`FormAdvice`（`beforeRender` / `beforeInstanceHandle` / `insertOrUpdate` / `afterInstanceHandle`）做业务扩展。客户/供应商/产品各自有 `XxxFormAdvice`。
- **配置驱动的报表**：`sys_report` 配置列表的 SQL/列/查询条件/导出；`ReportAdvice` 做行级增强（如解析操作人姓名、产品关联）。导航菜单里的“产品/客户/供应商/字典/用户/文件”都是报表页。
- **手动子表范式**：父实体上声明 `@OneToMany(cascadeInsertOrUpdate=false, cascadeQuery=false)`，在 DAO 的 `insertOrUpdate(T)` / `selectById(ID)` 里手工 diff 持久化与装载子表（客户跟进记录、采购计划、背景调查均如此）。
- **审计自动填充**：`BaseEntity` 含 `createBy/createTime/updateBy/updateTime/is_deleted`；`SharpConfig.fill()` 在 insert/update 时注入当前用户 ID，`createBy/createTime` 标记 `updatable=false` 编辑后保留。
- **逻辑删除**：全表 `is_deleted` bit；`EntityDAOImpl.deleteLogicallyById` 等方法。

---

## 三、业务功能

### 1. 产品管理（`module/product`）
- 产品维护：品名、分类（字典 `PRODUCT_TYPE`）、所属供应商（`@ManyToOne`）、RMB/USD 价格、参数描述、图片（OSS，`List<Map>`）、USD/RMB 报价模板、配件、卖点、证书、MOQ、交期、包装信息。
- **报价单导出**：`ExportPriceController` 基于客户 + 模板（`templates/excel/template-*.xlsx`）用 POI 生成 RMB/USD 报价 Excel。
- 支持复制新建后跳转详情。

### 2. 客户管理（`module/customer`）
- 客户档案：编号（`C` 前缀，按日序列）、名称、国家（按大洲分组的 `t_country` 字典，可搜索筛选）、联系人、电话/WhatsApp、邮箱、网站、社媒（Facebook/Instagram/YouTube/LinkedIn）、客户类型/来源/级别/现阶段状态、客户需求、备注。
- **采购计划 / 背景调查**：`Record` 子表（`@OneToMany` oneToOne，按 category 区分）。
- **跟进记录（两级主从）**：客户 → 多条跟进记录 → 多条明细；明细有日期/描述/完成与否；联动规则：明细全部完成→跟进自动完成；新增未完成明细→跟进自动取消完成；手动勾选跟进完成不影响明细。
- **跟进面板（`task.html` + `TaskController`）**：按客户分组展示「未完成跟进」下的「未完成明细」，可一键标记完成（同步联动跟进完成态），点击客户跳转编辑页；明细日期为当天/历史时红色高亮。

### 3. 供应商管理（`module/supplier`）
- 供应商档案：编号（`S` 前缀）、名称、地址、联系人、电话、附件（OSS）、备注。

### 4. 编号序列（`module/code`）
- `CodeSequenceService`：按 `(category, prefix, name=yyyyMMdd)` 维护日序列；格式 `prefix + yyyyMMdd + leftPad(seq, 2)`。
- **预览/提交分离**：`peekCodeSequence` 仅预览不占用序号（新增表单渲染时用），`getCodeSequence` 真正递增占用序号（保存时用）。未保存即离开，下次进入仍可用同一编号。

### 5. 系统（`sys`）
- 用户 / 角色 / 权限 RBAC；菜单通过 `sec:authorize` 按权限显隐。
- 字典管理（`sys_dict`）、文件/图床管理（OSS）。

### 6. 消息（`module/message`）
- 站内消息 + WebSocket（STOMP `/user/queue/message`）实时推送到浏览器。

### 7. 其它
- 登录验证码（Kaptcha）、密码修改、更新日志页（`changeLog.html`）、错误页（403/404/500）、i18n（`static/i18n/messages`）。

---

## 四、配置与运行

### 配置文件
- `application.yml`：通用配置（Thymeleaf、缓存、邮件、`sharp.database.entity-base-package` 实体扫描包、`dict.items` SQL 字典源、`app.version`/`app.domain`）。
- `application-dev.yml` / `application-prod.yml`：环境相关（数据源、OSS、端口等）。
- `ehcache.xml`、`kaptcha.properties`、`logback-spring.xml`。

### 字典（`dict.items`）
SQL 驱动的下拉数据源，例如用户、角色、供应商、客户、国家（按大洲分组的 `parent_name` 用于 `<sp:select group>`）。

### 本地运行
```bash
# 1. 准备 MySQL，导入初始数据
mysql -uroot -p < sql/sql-init.sql
# 2. 按需执行业务表 DDL（新表无自动建表，需手动跑）
#    如客户跟进记录：src/main/resources/sql/customer_followup.sql
# 3. 配置 application-dev.yml（数据源 / OSS / 邮件）
# 4. 启动
mvn spring-boot:run
# 或
mvn -q compile && java -Dspring.profiles.active=dev -jar target/product-manager-1.0-SNAPSHOT.jar
```

### 建表说明
项目无 Flyway/Liquibase。新实体通过 `TableGenerator.createTable(Class)`（见 `src/test/.../TableGeneratorTest.java`）或手写 DDL 创建。

### 部署与备份
- `deploy.sh`：停旧进程 → 替换 jar → `nohup java -jar ... --server.port=$port` 启动（prod profile）。
- `mysqldump.sh`：每日 `--single-transaction` 备份，保留 7 天。
- `config.sh`：部署变量。

---

## 五、目录约定

```
src/main/java/com/rick/manager/module/<domain>/
    entity/      # @Table 实体（继承 BaseEntity / BaseCodeEntity）
    dao/         # EntityDAOImpl 子类，含 insertOrUpdate/selectById 子表逻辑
    service/     # FormAdvice / ReportAdvice / Service
    controller/  # 仅在需要自定义端点时存在（如导出、跟进面板）
src/main/resources/templates/
    modules/     # 自定义表单模板（customer.html、product/product.html）
    tpl/         # formflow/report 通用模板（horizotal-form-tab、list 等）
    includes/base.html   # 公共头/脚本/多标签页 openOnNewTab
    index.html   # 主框架 + 菜单
sql/sql-init.sql         # 初始库结构 + 数据
src/main/resources/sql/  # 增量 DDL（手动执行）
```

---

## 六、测试

`src/test/java` 下含 `TableGeneratorTest`、`GeneratorTest`、`FormCpnRenderTest`、`ReportRenderTest`、`PermissionTest`、`DictTest`，以及各业务模块的 `ProductTest` / `CustomerTest` / `SupplierTest` 等，主要用于建表、代码生成与渲染验证。
