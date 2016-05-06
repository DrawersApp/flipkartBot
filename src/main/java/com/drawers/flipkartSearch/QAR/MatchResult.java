package com.drawers.flipkartSearch.QAR;

import com.drawers.dao.QAResponse;
import com.drawers.dao.QAResponseContainer;
import org.drawers.bot.lib.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nishant.pathak on 01/05/16.
 */
public class MatchResult implements Response {

    private ProductInfoList[] productInfoList;

    public String toJsonString() {
        if (productInfoList == null) {
            return null;
        }
        List<QAResponse> responseList = new ArrayList<>(productInfoList.length);

        for (int i = 0; i < productInfoList.length; i++) {
            responseList.add(i, new QAResponse(productInfoList[i].getProductBaseInfoV1().getImageUrls().getUnknown(),
                    productInfoList[i].getProductBaseInfoV1().getTitle(),
                    productInfoList[i].getProductBaseInfoV1().getProductDescription(),
                    new QAResponse.ActionableItem("Add to Cart", productInfoList[i].getProductBaseInfoV1().getProductUrl(), QAResponse.ReplyType.WEB),
                    new QAResponse.ActionableItem(productInfoList[i].getProductBaseInfoV1().getFlipkartSellingPrice().getDisplayPrice(), null, QAResponse.ReplyType.NA),
                    null));
        }
        QAResponseContainer container = new QAResponseContainer(responseList, null);

        return container.toJsonString();

    }

    @Override
    public String toString() {
        return toJsonString();
    }

    private class ProductInfoList {

        private ProductBaseInfoV1 productBaseInfoV1;

        public ProductBaseInfoV1 getProductBaseInfoV1() {
            return productBaseInfoV1;
        }

        @Override
        public String toString() {
            return "ProductInfoList{" +
                    "productBaseInfoV1=" + productBaseInfoV1 +
                    '}';
        }

        private class ProductBaseInfoV1 {
            private String title;
            private String productDescription;
            private FlipkartPrice flipkartSellingPrice;
            private ImageUrls imageUrls;
            private String productUrl;

            public FlipkartPrice getFlipkartSellingPrice() {
                return flipkartSellingPrice;
            }

            public String getProductUrl() {
                return productUrl;
            }

            public String getTitle() {
                return title;
            }

            public String getProductDescription() {
                return productDescription;
            }

            public ImageUrls getImageUrls() {
                return imageUrls;
            }

            @Override
            public String toString() {
                return "ProductInfoList{" +
                        "title='" + title + '\'' +
                        ", productDescription='" + productDescription + '\'' +
                        ", flipkartSellingPrice=" + flipkartSellingPrice +
                        '}';
            }

            private class FlipkartPrice {
                Float amount;
                String currency;

                public String getDisplayPrice() {
                    return amount + " " + currency;
                }

                @Override
                public String toString() {
                    return "FlipkartPrice{" +
                            "amount=" + amount +
                            ", currency='" + currency + '\'' +
                            '}';
                }
            }

            private class ImageUrls {
                private String unknown;

                public String getUnknown() {
                    return unknown;
                }
            }
        }
    }
}
