package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Division;
import utils.DBUtil;

public class DivisionValidator {
    public static List<String> validate(Division dv, Boolean nameDuplicateCheckFlag) {
        List<String> errors = new ArrayList<String>();

        String name_error = validateName(dv.getName(), nameDuplicateCheckFlag);
        if (!name_error.equals("")) {
            errors.add(name_error);
        }
        return errors;
    }

    // 課名
    private static String validateName(String name, Boolean nameDuplicateCheckFlag) {
        // 必須入力チェック
        if (name == null || name.equals("")) {
            return "課名を入力してください。";
        }

        // すでに登録されている課名との重複チェック
        if (nameDuplicateCheckFlag) {
            EntityManager em = DBUtil.createEntityManager();
            long divisions_count = (long) em.createNamedQuery("checkRegisteredName", Long.class)
                    .setParameter("name", name).getSingleResult();
            em.close();
            if (divisions_count > 0) {
                return "入力された課の情報はすでに存在しています。";
            }
        }
        return "";
    }
}