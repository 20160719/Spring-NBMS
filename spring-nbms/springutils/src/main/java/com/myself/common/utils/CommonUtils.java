package com.myself.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.myself.persistences.entity.Tree;
import com.myself.persistences.entity.system.Permission;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CommonUtils {

    private CommonUtils() {

    }

    /**
     * 将tree字符串转为List<Tree>集合
     * @param treeStr
     * @return
     */
    public static List<Tree> transTree(String treeStr) {
        return transTargetList(treeStr, Tree.class);
    }

    public static <T> List<T> transTargetList(String sourceStr, Class<T> clazz) {
        List<T> list = JSONArray.parseArray(sourceStr, clazz);
        Assert.notEmpty(list, "the list must not be empty");
        return list;
    }

    /**
     * 为Tree设置children属性
     * @param list
     * @param map
     * @return
     */
    public static List<Tree> setChildrenListForTree(List<Tree> list, Map<String, List<Tree>> map) {
        return list.stream().filter(t -> {
            t.setChildren(map.get(t.getId()));
            return true;
        }).collect(Collectors.toList());
    }

    /**
     * 为Tree设置children属性
     * @param list
     * @return
     */
    public static List<Tree> setChildrenListForTree(List<Tree> list) {
        Map<String, List<Tree>> map = treeGroupBy(list, Tree::getParentId);
        Assert.notEmpty(map, "the map must not be empty");
        return setChildrenListForTree(list, map);
    }

    /**
     *
     * @param list
     * @param tree
     * @return
     */
    public static List<Tree> filterTreeList(List<Tree> list, Tree tree) {
        return list.stream().filter(t -> t.equalContent(tree)).collect(Collectors.toList());
    }

    /**
     * 对Tree进行分组处理
     * @param list
     * @param classifier
     * @return
     */
    public static Map<String, List<Tree>> treeGroupBy(List<Tree> list, Function<Tree, String> classifier) {
        return list.stream().collect(Collectors.groupingBy(classifier));
    }

    /**
     * 根据roleId对List<Permission>进行过滤处理
     * @param list
     * @param splitList
     * @return
     */
    public static List<Permission> filterPermissionListById(List<Permission> list, List<String> splitList) {
        return list.stream().filter(p -> splitList.contains(p.getRoleId())).collect(Collectors.toList());
    }

    /**
     * 根据roleId对 List<Tree>进行过滤处理
     * @param list
     * @param splitList
     * @return
     */
    public static List<Tree> filterTreeListById(List<Tree> list, List<String> splitList) {
        return list.stream().filter(t -> splitList.contains(t.getId())).collect(Collectors.toList());
    }

    /**
     * 根据visibleList可见性对 List<Tree>进行过滤处理
     * @param list
     * @param visibleList
     * @return
     */
    public static List<Tree> filterTreeListByVisible(List<Tree> list, List<String> visibleList) {
        return list.stream().filter(t -> visibleList.contains(t.getVisible())).collect(Collectors.toList());
    }
    
    /**
     * 根据typeList类型性对 List<Tree>进行过滤处理
     * @param list
     * @param typeList
     * @return
     */
    public static List<Tree> filterTreeListByType(List<Tree> list, List<String> typeList) {
    	return list.stream().filter(t -> typeList.contains(t.getType())).collect(Collectors.toList());
    }
    
    /**
     * 根据typeList有效性对 List<Tree>进行过滤处理
     * @param list
     * @param typeList
     * @return
     */
    public static List<Tree> filterTreeListByValid(List<Tree> list, List<String> validList) {
    	return list.stream().filter(t -> validList.contains(t.getValid())).collect(Collectors.toList());
    }

    /**
     * 根据parentIdId对List<Tree>进行过滤处理
     * @param list
     * @param splitList
     * @return
     */
    public static List<Tree> filterTreeListByParentId(List<Tree> list, List<String> splitList) {
        return list.stream().filter(t -> splitList.contains(t.getParentId())).collect(Collectors.toList());
    }

//    public static <M> List<Tree> filterTreeListByParentId(List<Tree> list, List<String> splitList, Predicate<Tree> predicate) {
//        return list.stream().filter(predicate).collect(Collectors.toList());
//    }

}
