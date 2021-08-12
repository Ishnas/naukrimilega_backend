package com.naukrimilega.models;

import java.util.List;
import lombok.Data;

@Data
public class GkDetails {
  private GKType gkType;
  private List<String> generalKnowledge;
}
