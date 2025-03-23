package com.beyond3.yyGang.nsupplement.dto;

import com.beyond3.yyGang.hfunction.HFunctionName;
import com.beyond3.yyGang.ingredient.IngredientName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NSupplementResponseDtoV2 {

    @Schema(description = "제품명")
    private String productName;

    @Schema(description = "주의사항")
    private String caution;

    @Schema(description = "브랜드")
    private String brand;

    @Schema(description = "가격")
    private int price;

    private List<IngredientName> ingredients = new ArrayList<>();

    private List<HFunctionName> healthNames = new ArrayList<>();

    public NSupplementResponseDtoV2(String productName, String caution, String brand, int price) {
        this.productName = productName;
        this.caution = caution;
        this.brand = brand;
        this.price = price;
    }

    public void addIngredient(IngredientName ingredient) {
        this.ingredients.add(ingredient);
    }

    public void addHealthName(HFunctionName healthName) {
        this.healthNames.add(healthName);
    }

}
