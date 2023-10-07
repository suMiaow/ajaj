package com.meme.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestCompany {

    private String code;

    @SerializedName("nnnn")
    private String name;

    @SerializedName("aaa_list")
    private List<String> aaaList;
}
