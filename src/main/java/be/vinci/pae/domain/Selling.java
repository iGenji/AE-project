package be.vinci.pae.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = SellingImpl.class)
public interface Selling extends SellingDTO {

}
