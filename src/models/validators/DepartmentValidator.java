package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Division;
import utils.DBUtil;

public class DepartmentValidator {
    public static List<String> validate(Division dp, Boolean nameDuplicateCheckFlag) {
        List<String> errors = new ArrayList<String>();

        String name_error = validateName(dp.getName(), nameDuplicateCheckFlag);
        if (!name_error.equals("")) {
            errors.add(name_error);
        }
        return errors;
    }

    // 部署名
    private static String validateName(String name, Boolean nameDuplicateCheckFlag) {
        // 必須入力チェック
        if (name == null || name.equals("")) {
            return "部署名を入力してください。";
        }

        // すでに登録されている部署名との重複チェック
        if (nameDuplicateCheckFlag) {
            EntityManager em = DBUtil.createEntityManager();
            long departments_count = (long) em.createNamedQuery("checkRegisteredName", Long.class)
                    .setParameter("name", name).getSingleResult();
            em.close();
            if (departments_count > 0) {
                return "入力された部署の情報はすでに存在しています。";
            }
        }
        return "";
    }
}