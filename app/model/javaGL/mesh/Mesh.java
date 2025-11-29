package model.javaGL.mesh;

import java.util.HashSet;
import java.util.Set;

import model.javaGL.matrix.DoubleMatrix;
import model.javaGL.matrix.Matrix;
import model.javaGL.matrix.MatrixMath;
import model.javaGL.mesh.primitives.Primitive;

/**
 * Represents a mesh object: an object made up of primitives, especially triangles.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public class Mesh {
    private Matrix<Double> iTransformedOrigin;
    private final Set<Primitive> iPrimitives;
    private String iName;

    {
        this.iPrimitives = new HashSet<>();
        this.iTransformedOrigin = DoubleMatrix.identity(4);
    }

    /**
     * Constructs a basic mesh object with zero primitives.
     */
    public Mesh() {
        super();
    }

    /**
     * Sets the name of this mesh
     * @param pName the name of this mesh
     */
    public void setName(final String pName) {
        this.iName = pName;
    }

    /**
     * returns the name of this mesh
     * @return this name of this mesh
     */
    public String getName() {
        return this.iName;
    }

    /**
     * Gets the primitives that make up this mesh
     * @return the set of primitives that make up this mesh
     */
    public Set<Primitive> primitives() {
        return this.iPrimitives;
    }

    /**
     * Adds the primitive to the mesh.
     * @param pPrimitive the primitive to add
     */
    public void add(final Primitive pPrimitive) {
        this.iPrimitives.add(pPrimitive);
    }

    /**
     * Removes the primitive from the mesh
     * @param pPrimitive the primitive to remove
     */
    public void remove(final Primitive pPrimitive) {
        this.iPrimitives.remove(pPrimitive);
    }

    /**
     * Applies a transformation on this mesh, specifically to this mesh's origin
     * @param pTransformation the transformation matrix
     */
    public void transform(final Matrix<Double> pTransformation) {
        if (pTransformation.rowCount() != 4 && pTransformation.columnCount() != 4)
            throw new IllegalArgumentException("Transformation matrices for meshes must be 4x4");

        this.iTransformedOrigin = MatrixMath.matrixMultiply(pTransformation, this.iTransformedOrigin);
    }

    /**
     * Adds the matrix to this mesh's transform
     * @param pTransform the matrix to add
     */
    public void addTransfrom(final Matrix<Double> pTransform) {
        this.iTransformedOrigin = MatrixMath.addMatrix(this.iTransformedOrigin, pTransform);
    }
}
