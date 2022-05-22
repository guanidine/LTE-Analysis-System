package team.lte.aclservice.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import team.lte.aclservice.entity.po.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据权限数据构建菜单数据。
 */
public class PermissionUtils {

    /** 递归构建权限树形结构 */
    public static List<Permission> listPermissionsReverse(List<Permission> nodes) {
        List<Permission> trees = new ArrayList<>();
        for (Permission node : nodes) {
            if (node.getPid() == 0) {
                node.setLevel(1);
                trees.add(listChildPermissions(node, nodes));
            }
        }
        return trees;
    }

    /** 构建前端router中的权限结构JSON */
    public static List<JSONObject> buildRouterPermissions(List<Permission> nodes) {
        List<JSONObject> menus = new ArrayList<>();
        if (nodes.size() == 1) {
            Permission topNode = nodes.get(0);
            // 左侧一级菜单
            List<Permission> oneMenuList = topNode.getChildren();
            for (Permission one : oneMenuList) {
                JSONObject oneMenu = new JSONObject();
                oneMenu.put("path", one.getPath());
                oneMenu.put("component", one.getComponent());
                oneMenu.put("redirect", "noredirect");
                oneMenu.put("name", "name_" + one.getId());
                oneMenu.put("hidden", false);

                JSONObject oneMeta = new JSONObject();
                oneMeta.put("title", one.getName());
                oneMeta.put("icon", one.getIcon());
                oneMenu.put("meta", oneMeta);

                List<JSONObject> children = new ArrayList<>();
                List<Permission> twoMenuList = one.getChildren();
                for (Permission two : twoMenuList) {
                    JSONObject twoMenu = new JSONObject();
                    twoMenu.put("path", two.getPath());
                    twoMenu.put("component", two.getComponent());
                    twoMenu.put("name", "name_" + two.getId());
                    twoMenu.put("hidden", false);

                    JSONObject twoMeta = new JSONObject();
                    twoMeta.put("title", two.getName());
                    twoMenu.put("meta", twoMeta);

                    children.add(twoMenu);

                    List<Permission> threeMenuList = two.getChildren();
                    for (Permission three : threeMenuList) {
                        if (StringUtils.isEmpty(three.getPath())) {
                            continue;
                        }

                        JSONObject threeMenu = new JSONObject();
                        threeMenu.put("path", three.getPath());
                        threeMenu.put("component", three.getComponent());
                        threeMenu.put("name", "name_" + three.getId());
                        threeMenu.put("hidden", true);

                        JSONObject threeMeta = new JSONObject();
                        threeMeta.put("title", three.getName());
                        threeMenu.put("meta", threeMeta);

                        children.add(threeMenu);
                    }
                }
                oneMenu.put("children", children);
                menus.add(oneMenu);
            }
        }
        return menus;
    }

    /** 递归查找子节点 */
    private static Permission listChildPermissions(Permission root, List<Permission> nodes) {
        root.setChildren(new ArrayList<>());

        for (Permission node : nodes) {
            if (root.getId().equals(node.getPid())) {
                node.setLevel(root.getLevel() + 1);
                root.getChildren().add(listChildPermissions(node, nodes));
            }
        }
        return root;
    }
}
