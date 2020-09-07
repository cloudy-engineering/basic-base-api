package com.brightfield.basic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.time.ZonedDateTime;

@Path("/v1/base/product")
@Produces("application/json")
public class BasicResource {

    private Logger log = LoggerFactory.getLogger(BasicResource.class);

    @GET
    @Path("/{product_id}")
    public Response getProductById(@PathParam("product_id") Integer id) {
        Product product = new Product();
        product.setProductId(id);
        product.setName("Product A");
        product.setCategory("Perishable");
        product.setCreated(ZonedDateTime.now());

        return Response.ok(product).build();
    }

    class Product {
        @JsonProperty("product_id")
        private Integer productId;

        @JsonProperty("product_name")
        private String name;

        private String category;

        @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
        @JsonSerialize(using = ZonedDateTimeSerializer.class)
        private java.time.ZonedDateTime created;

        public Integer getProductId() {
            return productId;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public ZonedDateTime getCreated() {
            return created;
        }

        public void setCreated(ZonedDateTime created) {
            this.created = created;
        }
    }
}
