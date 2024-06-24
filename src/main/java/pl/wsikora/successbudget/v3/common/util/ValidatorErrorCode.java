package pl.wsikora.successbudget.v3.common.util;

public interface ValidatorErrorCode {

	String E_FIELD_MUST_NOT_BE_EMPTY = "field.must.not.be.empty";

	String E_FIELD_MUST_CONTAIN_VALID_VALUE = "field.must.contain.valid.value";

	String E_DATE_MUST_HAVE_FORMAT = "date.must.have.format";

	String E_DATE_MUST_BE_IN_FUTURE = "date.must.be.in.future";

	String E_FIELD_MUST_CONTAIN_UP_TO_NUMBER_OF_CHARACTERS = "field.must.contain.up.to.number.of.characters";

	String E_FIELD_MUST_CONTAIN_SPECIFIC_NUMBER_OF_CHARACTERS = "field.must.contain.specific.number.of.characters";

	String E_VALUE_MUST_BE_WITHIN_ALLOWED_RANGE = "value.must.be.within.allowed.range";

	String E_VALUE_MUST_BE_GREATER_THAN_OR_EQUAL_TO_VALUE = "value.must.be.greater.than.or.equal.to.value";

	String E_FIELD_VALUE_IS_TOO_HIGH = "field.value.is.too.high";

	String E_FIELD_VALUE_IS_TOO_LOW = "field.value.is.too.low";

	String E_FIELD_VALUE_MUST_BE_UNIQUE = "field.value.must.be.unique";
}
