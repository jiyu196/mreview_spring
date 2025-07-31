package com.kiylab.mreview.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.util.UriComponentsBuilder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieImageDTO {
  private String origin;
  private String uuid;
  private String path;
  private Long mno;

  public String getUrl() {
    UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
    builder.queryParam("origin", origin);
    builder.queryParam("uuid", uuid);
    builder.queryParam("path", path);
    return builder.build().toUriString();
  }
  public String getThumb() {
    UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
    builder.queryParam("origin", origin);
    builder.queryParam("uuid", "s_" + uuid);  // 파일명 앞에 있기 때문에 s를 그쪽에 붙이는거임
    builder.queryParam("path", path);
    return builder.build().toUriString();  // 원본도, 썸네일도 거기서 보여줘야하는거임
  }
}
