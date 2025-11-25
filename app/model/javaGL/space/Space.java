package model.javaGL.space;

import java.util.HashMap;
import java.util.Map;

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
     * Copies and translates the mesh to this space's dimensions
     * @param pMesh the mesh to translate into this space
     * @param pTransition the transition matrix that will correctly translate the mesh
     *                    into this space's coordinates
     */
    public void transformCopy(final Mesh pMesh, final DoubleMatrix pTransition) {
        final Mesh lNewMesh = new Mesh();
        for (final Primitive p : pMesh.primitives()) {
            final Primitive lCopy = p.copy();
            lCopy.transform(pTransition);
            lNewMesh.add(lCopy);
        }
        this.iMeshes.put(lNewMesh.getName(), lNewMesh);
    }

    /**
     * Performs a transformation on an existing matrix in this space.
     * Does nothing if the mesh does not exist
     * @param pMeshName the name of the mesh
     * @param pTransform the transformation matrix
     */
    public void transformExisting(final String pMeshName, final DoubleMatrix pTransform) {
        final Mesh lMesh = this.iMeshes.get(pMeshName);
        if (lMesh != null) lMesh.transform(pTransform);
    }

    /**
     * Appens a mesh to the space.
     * @param pMesh the mesh to append
     */
    public void addMesh(final Mesh pMesh) {
        this.iMeshes.put(pMesh.getName(), pMesh);
    }
}
