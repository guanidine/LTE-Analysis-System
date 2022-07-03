package team.lte.aclservice.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Route {
    private String path;
    private String name;
    private RouteMeta meta;
}
