package com.rick.manager.module.code.service;

import com.rick.common.util.Time2StringUtils;
import com.rick.db.plugin.dao.core.EntityDAO;
import com.rick.db.util.OptionalUtils;
import com.rick.manager.module.code.entity.CodeSequence;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.Instant;
import java.util.Optional;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Validated
public class CodeSequenceService {

    EntityDAO<CodeSequence, Long> codeSequenceDAO;

    /**
     * 默认
     *  name: yyyymmdd
     *  sequenceLen: 2
     * @param category
     * @param prefix
     * @return
     */
    public String getCodeSequence(String category, String prefix) {
        return getCodeSequence(category, prefix, Time2StringUtils.format(Instant.now()).replaceAll("\\s+|-|:", "").substring(0, 8), 2);
    }

    public String getCodeSequence(String category, String prefix, String name, int sequenceLen) {
        return getCodeSequences(category, prefix, name, sequenceLen, 1)[0];
    }

    /**
     *
     * @param category 分类
     * @param prefix 前缀
     * @param name
     * @param sequenceLen 补齐的长度
     * @param size 一次性获取 code 的数量
     * @return
     */
    public String[] getCodeSequences(String category, String prefix, String name, int sequenceLen, int size) {
        int[] sequences = getNextSequences(category, prefix, name, size);

        String[] codeSequences = new String[size];

        for (int i = 0; i < size; i++) {
            codeSequences[i] = StringUtils.defaultString(prefix, "") + name + StringUtils.leftPad("" + sequences[i], sequenceLen, "0");
        }

        return codeSequences;
    }


    public int getNextSequence(String category, String prefix, String name) {
        return getNextSequences(category, prefix, name, 1)[0];
    }

    /**
     *
     * @param prefix 前缀
     * @param name
     * @param size 获取序列的总数量
     * @return
     */
    public int[] getNextSequences(String category, String prefix, String name, int size) {
        Optional<CodeSequence> codeSequenceOptional = OptionalUtils.expectedAsOptional(codeSequenceDAO.selectByParams(CodeSequence.builder()
                .category(category)
                .prefix(prefix)
                .name(name)
                .build()));

        int sequence;
        if (codeSequenceOptional.isPresent()) {
            CodeSequence codeSequence = codeSequenceOptional.get();
            sequence = codeSequence.getSequence();
        } else {
            sequence = 0;
        }

        codeSequenceDAO.update("sequence, name", new Object[]{ sequence + size, name, category, prefix }, "category = ? AND prefix = ?");

        int[] sequences = new int[size];

        for (int i = 1; i <= size; i++) {
            sequences[i - 1] = sequence + i;
        }

        return sequences;
    }

}