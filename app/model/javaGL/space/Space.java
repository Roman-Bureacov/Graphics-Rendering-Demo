package model.javaGL.space;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.javaGL.matrix.DoubleMatrix;
import model.javaGL.mesh.Mesh;
import model.javaGL.mesh.primitives.Primitive;

/**
 * Representation of a space.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public class Space {
    final Map<String, Mesh> iMeshes = new HashMap<>();

    /**
     * Performs a transformation on an existing mesh in this space.
     * Does nothing if the mesh does not exist
     * @param pMeshName the name of the mesh
     * @param pTransform the transformation matrix
     */
    public void transformExisting(final String pMeshName, final DoubleMatrix pTransform) {
        final Mesh lMesh = this.iMeshes.get(pMeshName);
        if (lMesh != null) lMesh.transform(pTransform);
    }

    /**
     * Appends a mesh to the space.
     * @param pMesh the mesh to append
     */
    public void addMesh(final Mesh pMesh) {
        this.iMeshes.put(pMesh.getName(), pMesh);
    }

    /**
     * Retrieves the mesh names contained in this space
     * @return the mesh names in this space
     */
    public Set<String> meshes() {
        return this.iMeshes.keySet();
    }
}
