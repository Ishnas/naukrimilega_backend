package com.naukrimilega.models.query;

public enum CategoryValue {
  ANDHRA_PRADESH("andhrapradesh"),
  ARUNACHAL_PRADESH("arunachalpradesh"),
  ASSAM("assam"),
  BIHAR("bihar"),
  CHHATTISGARH("chhattisgarh"),
  GOA("goa"),
  GUJARAT("gujarat"),
  HARYANA("haryana"),
  HIMACHAL_PRADESH("himachalpradesh"),
  JHARKHAND("jharkhand"),
  KARNATAKA("karnataka"),
  KERALA("kerala"),
  MADHYA_PRADESH("madhyapradesh"),
  MAHARASHTRA("maharashtra"),
  MANIPUR("manipur"),
  MEGHALAYA("meghalaya"),
  MIZORAM("mizoram"),
  NAGALAND("nagaland"),
  ORISSA("orissa"),
  PUNJAB("punjab"),
  RAJASTHAN("rajasthan"),
  SIKKIM("sikkim"),
  TAMILNADU("tamilnadu"),
  TELANGANA("telangana"),
  TRIPURA("tripura"),
  UTTAR_PRADESH("uttarpradesh"),
  UTTARAKHAND("uttarakhand"),
  WEST_BENGAL("westbengal"),
  ANDAMAN_AND_NICOBAR("andamanandnicobar"),
  CHANDIGARH("chandigarh"),
  DADRA_AND_NAGAR_HAVELI_AND_DAMAN_AND_DIU("dadraandnagarhavelianddamananddiu"),
  DELHI("delhi"),
  JAMMU_AND_KASHMIR("jammuandkashmir"),
  LADAKH("ladakh"),
  LAKSHADWEEP("lakshadweep"),
  PUDUCHERRY("puducherry");

  private String actualValue;

  CategoryValue(String actualValue) {
    this.actualValue = actualValue;
  }

  public String getActualValue() {
    return actualValue;
  }

  public static CategoryValue getCategoryValueEnum(String value) {
    switch(value) {
      case "andhrapradesh": return CategoryValue.ANDHRA_PRADESH;
      case "arunachalpradesh": return CategoryValue.ARUNACHAL_PRADESH;
      case "assam": return CategoryValue.ASSAM;
      case "bihar": return CategoryValue.BIHAR;
      default: throw new RuntimeException("Not implemented yet");
    }
  }
}
