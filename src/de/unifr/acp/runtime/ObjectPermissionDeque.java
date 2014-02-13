package de.unifr.acp.runtime;

import java.util.Deque;
import java.util.Map;

import de.unifr.acp.runtime.fst.Permission;

public interface ObjectPermissionDeque extends Deque<Map<Object, Map<String, Permission>>> {

}
