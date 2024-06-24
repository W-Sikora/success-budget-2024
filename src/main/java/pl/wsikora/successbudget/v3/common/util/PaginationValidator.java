package pl.wsikora.successbudget.v3.common.util;

import static java.util.Objects.nonNull;


public class PaginationValidator {

    private PaginationValidator() {}

    public static boolean isValid(Integer page, Integer size) {

        return nonNull(page) && nonNull(size)
            && page >= 0 && size >= 0;
    }

    public static boolean isNotValid(Integer page, Integer size) {

        return !isValid(page, size);
    }
}
