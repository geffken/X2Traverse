package de.unifr.acp.templates;

import java.util.Deque;
import java.util.Map;

import de.unifr.acp.fst.Permission;

public interface ObjectPermissionDeque extends Deque<Map<Object, Map<String, Permission>>> {

}
