# TODO / 扩展点

按优先级与主题分组。每条尽量给出动机与大致落点，便于后续认领实现。

---

## P0 · 数据安全与正确性

- [ ] **编号序列的并发安全**：`CodeSequenceService.peekCodeSequence`（渲染预览）与 `getCodeSequence`（保存占用）之间存在时间窗，并发同时保存可能让两人拿到相同预览编号；当前靠 `getCodeSequence` 在保存时再次递增兜底，但“展示编号”与“最终编号”可能不一致。建议：保存时占用序号改为数据库行锁/`UPDATE ... RETURNING` 或原子自增查询，并把“最终编号”回写给前端刷新。
- [ ] **CSRF 当前全局关闭**（`WebSecurityConfig.csrf().disable()`）。考虑为状态变更类 POST 单独启用 CSRF，或改用 token 鉴权，降低被恶意表单提交的风险。
- [ ] **数据库迁移框架缺失**：无 Flyway/Liquibase，新表靠手动 DDL（`src/main/resources/sql/*.sql`）+ `TableGeneratorTest`。环境间易漂移。建议引入 Flyway，把现有 `sql-init.sql`/`customer_followup.sql` 纳入版本化迁移。
- [ ] **逻辑删除无回收站**：`is_deleted=1` 后无 UI 查看/恢复。建议加“回收站”报表页，或在 `EntityDAOImpl` 增加 `restoreById`。
- [ ] **审计字段为人用户 ID 而非姓名**：客户跟进记录页面已移除 `createByName/updateByName` 以避免 N+1；可在 `ReportAdvice` 层统一批量解析 `sys_user` 姓名，形成通用增强（参考 `OperatorReportAdvice`）。

## P1 · 客户/跟进业务

- [ ] **跟进面板筛选**：`task.html` 目前一次性列出全部未完成明细。增加按客户名/日期范围/状态过滤、分页，以及批量“标记完成”。
- [ ] **逾期跟进提醒**：明细日期为当天/历史已红色高亮（`task.html`），可结合 `module/message` 的 WebSocket 推送，对逾期未完成的跟进定时提醒责任人。
- [ ] **跟进记录附件/评论**：明细目前只有日期+描述+完成。外贸场景常需附报价截图、邮件往来，可加 OSS 附件字段或子评论。
- [ ] **客户编号手动编辑保留**：客户用自定义模板 + 隐藏 `previewCode` 字段保留了手动改码；供应商走通用模板无法注入隐藏字段，手动改码会被覆盖。可统一给通用模板加一个无害的 `previewCode` 隐藏域，或给供应商做自定义模板。
- [ ] **新建客户“编号”字段可编辑**：编号实为系统生成，新建时却未禁用编辑，易被误改。建议新建态也置只读，仅展示预览。

## P2 · 产品/报价业务

- [ ] **报价单导出扩展**：当前仅产品报价（RMB/USD 模板）。可扩展为客户清单导出、供应商清单导出、跟进历史导出（复用 `sharp-excel`）。
- [ ] **产品图片存储优化**：`List<Map<String,String>>` 存为 text 列，元数据与图片耦合。建议复用 `sharp-fileupload` 的 `sys_document` 表，产品仅持有关联 id。
- [ ] **配件/卖点/交期/包装**：现为 `List<List<String>>` 弱结构。若有统计/筛选需求，可拆为标准子表。
- [ ] **产品复制新建**：已有复制后跳转详情，可补“复制时一并带出图片/配件”的选项。

## P3 · 平台与工程

- [ ] **REST API**：当前对外能力几乎都走表单/报表页，无机器可消费的 API。可为产品/客户/供应商暴露只读 REST（`@RestController` + `BaseFormController` 已有雏形），便于对接 ERP/官网。
- [ ] **真正的仪表盘**：`dashboard.html` 目前是占位 demo。可做统计卡（客户按状态分布、逾期跟进数、产品总数、本月新增）。
- [ ] **国际化**：`messages` basename 已配，但文案覆盖不全。补齐 `messages_en.properties` 并切换语言。
- [ ] **更新日志自动化**：`changeLog.html` 手写。可从 git tag / conventional commits 自动生成。
- [ ] **操作审计**：仅有 `createBy/updateBy`。关键业务（删客户、改价格）建议落独立审计表（谁、何时、改了哪些字段、旧值新值）。
- [ ] **测试覆盖**：现有测试偏重建表/渲染。补关键 DAO 子表 diff 逻辑（`CustomerDAO.saveFollowUps`/`saveDetails`）、`CodeSequenceService` peek/commit、`TaskController` 联动完成的单元测试。
- [ ] **国家字典**：`t_country` 用硬编码 `case when` 映射大洲。建议建规范 `t_continent` 关联表，字典 SQL 更干净。
- [ ] **索引核查**：`instance_id`/`followup_id`/`is_deleted`/`completed` 等高频过滤列核查索引，跟进面板查询随数据量增长需保证走索引。
- [ ] **备份可观测**：`mysqldump.sh` 仅写日志。加备份成功/失败告警（邮件或站内消息），并定时校验备份可恢复。
