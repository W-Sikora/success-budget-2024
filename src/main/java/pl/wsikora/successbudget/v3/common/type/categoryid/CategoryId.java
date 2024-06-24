package pl.wsikora.successbudget.v3.common.type.categoryid;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.util.Assert;


@Embeddable
@Access(AccessType.FIELD)
public class CategoryId {

    public static final String F_CATEGORY_ID = "categoryId";

    @Column(name = "category_id")
    private Long categoryId;

    protected CategoryId() {}

    private CategoryId(Long categoryId) {

        this.categoryId = categoryId;
    }

    public static CategoryId of(Long categoryId) {

        Assert.notNull(categoryId, "categoryId must not be null");

        return new CategoryId(categoryId);
    }

    public Long getCategoryId() {

        return categoryId;
    }
}
