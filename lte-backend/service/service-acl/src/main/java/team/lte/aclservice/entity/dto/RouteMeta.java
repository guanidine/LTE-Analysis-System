package team.lte.aclservice.entity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class RouteMeta {
    private String title;
    private String icon;
    private String[] roles;
}
