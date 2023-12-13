package vn.edu.iuh.fit.week05_nguyenlemychau_20046631.backend.enums;

import lombok.Getter;

@Getter
public enum SkillLevel {
    TEST(0),
    BEGINER(1),
    IMTERMEDIATE(2),
    ADVANCED(3),
    PROFESSIONAL(4),
    MASTER(5);

    private int value;

    SkillLevel(int value) {
    }


}
