package model.javaGL.mesh;

import java.util.HashSet;
import java.util.Set;

import model.javaGL.mesh.primitives.Primitive;

/**
 * Represents a mesh object: an object made up of primitives, especially triangles.
 *
 * @author Roman Bureacov
 * @version 2025-11
 */
public class Mesh {
    private final Set<Primitive> iPrimitives;

    {
        this.iPrimitives = new HashSet<>();
    }

    /**
     * Constructs a basic mesh object with zero primitives.
     */
    public Mesh() {
        super();
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
}
