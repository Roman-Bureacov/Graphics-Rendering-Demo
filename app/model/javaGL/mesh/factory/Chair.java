package model.javaGL.mesh.factory;

import model.javaGL.mesh.Mesh;
import model.javaGL.mesh.primitives.Triangle;

/**
 * Creates a chair mesh.
 * 
 * @author Roman Bureacov
 * @version 2025-11
 */
@SuppressWarnings("UnqualifiedFieldAccess")
class Chair extends Mesh {
    
    Triangle t;

    /**
     * Creates a basic chair mesh.
     */
    Chair() {
        super();
        this.makePrimitives();
        this.setName("Chair");
    }
    
    private void makePrimitives() {
        this.frontLeftLeg();
        this.frontRightLeg();
        this.backLeftLeg();
        this.backRightLeg();
        this.seat();
        this.backRest();
    }
    
    @SuppressWarnings("MagicNumber")
    private void frontRightLeg() {
        t = MeshFactory.makeTriangle(
                1d, 1d, 0d,
                1d, 1.25d, 0d,
                1.25d, 1d, 0d
        );
        this.add(t);

        t = MeshFactory.makeTriangle(
                1.25d, 1.25d, 0d,
                1d, 1.25d, 0d,
                1.25d, 1d, 0d
        );
        this.add(t);

        // walls
        t = MeshFactory.makeTriangle(
                1d, 1d, 0d,
                1d, 1.25d, 0d,
                1d, 1d, 0.5d
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                1d, 1d, 0.5d,
                1d, 1.25d, 0d,
                1d, 1d, 0.5d
        );
        this.add(t);

        t = MeshFactory.makeTriangle(
                1d, 1.25d, 0d,
                1.25d, 1.25d, 0d,
                1d, 1.25d, 0.5d
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                1.25d, 1.25d, 0d,
                1.25d, 1.25d, 0.5d,
                1d, 1.25d, 0.5d
        );
        this.add(t);

        t = MeshFactory.makeTriangle(
                1.25d, 1.25d, 0d,
                1.25d, 1d, 0d,
                1.25d, 1d, 0.5d
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                1.25d, 1.25d, 0d,
                1.25d, 1.25d, 0.5d,
                1.25d, 1d, 0.5d
        );
        this.add(t);


        t = MeshFactory.makeTriangle(
                1d, 1d, 0d,
                1.25d, 1d, 0d,
                1.25d, 1d, 0.5d
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                1d, 1d, 0d,
                1d, 1d, 0.5d,
                1.25d, 1d, 0.5d
        );
        this.add(t);
    }

    @SuppressWarnings("MagicNumber")
    private void frontLeftLeg() {
        t = MeshFactory.makeTriangle(
                -1d, 1d, 0d,
                -1d, 1.25d, 0d,
                -1.25d, 1d, 0d
        );
        this.add(t);

        t = MeshFactory.makeTriangle(
                -1.25d, 1.25d, 0d,
                -1d, 1.25d, 0d,
                -1.25d, 1d, 0d
        );
        this.add(t);

        // walls
        t = MeshFactory.makeTriangle(
                -1d, 1d, 0d,
                -1d, 1.25d, 0d,
                -1d, 1d, 0.5d
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                -1d, 1d, 0.5d,
                -1d, 1.25d, 0d,
                -1d, 1d, 0.5d
        );
        this.add(t);

        t = MeshFactory.makeTriangle(
                -1d, 1.25d, 0d,
                -1.25d, 1.25d, 0d,
                -1d, 1.25d, 0.5d
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                -1.25d, 1.25d, 0d,
                -1.25d, 1.25d, 0.5d,
                -1d, 1.25d, 0.5d
        );
        this.add(t);

        t = MeshFactory.makeTriangle(
                -1.25d, 1.25d, 0d,
                -1.25d, 1d, 0d,
                -1.25d, 1d, 0.5d
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                -1.25d, 1.25d, 0d,
                -1.25d, 1.25d, 0.5d,
                -1.25d, 1d, 0.5d
        );
        this.add(t);


        t = MeshFactory.makeTriangle(
                -1d, 1d, 0d,
                -1.25d, 1d, 0d,
                -1.25d, 1d, 0.5d
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                -1d, 1d, 0d,
                -1d, 1d, 0.5d,
                -1.25d, 1d, 0.5d
        );
        this.add(t);
    }

    @SuppressWarnings("MagicNumber")
    private void backLeftLeg() {
        t = MeshFactory.makeTriangle(
               -1d, -1d, 0d,
               -1d, -1.25d, 0d,
               -1.25d, -1d, 0d
        );
        this.add(t);

        t = MeshFactory.makeTriangle(
               -1.25d, -1.25d, 0d,
               -1d, -1.25d, 0d,
               -1.25d, -1d, 0d
        );
        this.add(t);

        // walls
        t = MeshFactory.makeTriangle(
               -1d, -1d, 0d,
               -1d, -1.25d, 0d,
               -1d, 1d, -0.5d
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
               -1d, 1d, -0.5d,
               -1d, -1.25d, 0d,
               -1d, 1d, -0.5d
        );
        this.add(t);

        t = MeshFactory.makeTriangle(
               -1d, -1.25d, 0d,
               -1.25d, -1.25d, 0d,
               -1d, 1.25d, -0.5d
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
               -1.25d, -1.25d, 0d,
               -1.25d, 1.25d, -0.5d,
               -1d, 1.25d, -0.5d
        );
        this.add(t);

        t = MeshFactory.makeTriangle(
               -1.25d, -1.25d, 0d,
               -1.25d, -1d, 0d,
               -1.25d, 1d, -0.5d
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
               -1.25d, -1.25d, 0d,
               -1.25d, 1.25d, -0.5d,
               -1.25d, 1d, -0.5d
        );
        this.add(t);


        t = MeshFactory.makeTriangle(
               -1d, -1d, 0d,
               -1.25d, -1d, 0d,
               -1.25d, 1d, -0.5d
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
               -1d, -1d, 0d,
               -1d, 1d, -0.5d,
               -1.25d, 1d, -0.5d
        );
        this.add(t);

    }

    @SuppressWarnings("MagicNumber")
    private void backRightLeg() {
        t = MeshFactory.makeTriangle(
                1d, -1d, 0d,
                1d, -1.25d, 0d,
                1.25d, -1d, 0d
        );
        this.add(t);

        t = MeshFactory.makeTriangle(
                1.25d, -1.25d, 0d,
                1d, -1.25d, 0d,
                1.25d, -1d, 0d
        );
        this.add(t);

        // walls
        t = MeshFactory.makeTriangle(
                1d, -1d, 0d,
                1d, -1.25d, 0d,
                1d, -1d, 0.5d
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                1d, -1d, 0.5d,
                1d, -1.25d, 0d,
                1d, -1d, 0.5d
        );
        this.add(t);

        t = MeshFactory.makeTriangle(
                1d, -1.25d, 0d,
                1.25d, -1.25d, 0d,
                1d, -1.25d, 0.5d
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                1.25d, -1.25d, 0d,
                1.25d, -1.25d, 0.5d,
                1d, -1.25d, 0.5d
        );
        this.add(t);

        t = MeshFactory.makeTriangle(
                1.25d, -1.25d, 0d,
                1.25d, -1d, 0d,
                1.25d, -1d, 0.5d
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                1.25d, -1.25d, 0d,
                1.25d, -1.25d, 0.5d,
                1.25d, -1d, 0.5d
        );
        this.add(t);


        t = MeshFactory.makeTriangle(
                1d, -1d, 0d,
                1.25d, -1d, 0d,
                1.25d, -1d, 0.5d
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                1d, -1d, 0d,
                1d, -1d, 0.5d,
                1.25d, -1d, 0.5d
        );
        this.add(t);
    }

    @SuppressWarnings("MagicNumber")
    private void seat() {
        // seat starts at z = 0.5
        // floor
        t = MeshFactory.makeTriangle(
                1.4, 1.4, 0.5,
                -1.4, -1.4, 0.5,
                1.4, -1.4, 0.5
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                -1.4, 1.4, 0.5,
                -1.4, -1.4, 0.5,
                1.4, -1.4, 0.5
        );
        this.add(t);
        
        // ceiling
        // seat starts at z = 0.5
        t = MeshFactory.makeTriangle(
                1.4, 1.4, 0.65,
                -1.4, -1.4, 0.65,
                1.4, -1.4, 0.65
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                -1.4, 1.4, 0.65,
                -1.4, -1.4, 0.65,
                1.4, -1.4, 0.65
        );
        this.add(t);
        
        // walls
        // front/back
        t = MeshFactory.makeTriangle(
                -1.4, 1.4, 0.5,
                1.4, 1.4, 0.5,
                1.4, 1.4, 0.65
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                -1.4, 1.4, 0.5,
                -1.4, 1.4, 0.65,
                1.4, 1.4, 0.65
        );
        this.add(t);

        t = MeshFactory.makeTriangle(
                -1.4, -1.4, 0.5,
                1.4, -1.4, 0.5,
                1.4, -1.4, 0.65
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                -1.4, -1.4, 0.5,
                -1.4, -1.4, 0.65,
                1.4, -1.4, 0.65
        );
        this.add(t);
        
        // left/right
        t = MeshFactory.makeTriangle(
                1.4, 1.4, 0.5,
                1.4, -1.4, 0.5,
                1.4, -1.4, 0.65
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                1.4, 1.4, 0.5,
                1.4, 1.4, 0.65,
                1.4, -1.4, 0.65
        );
        this.add(t);

        t = MeshFactory.makeTriangle(
                -1.4, 1.4, 0.5,
                -1.4, -1.4, 0.5,
                -1.4, -1.4, 0.65
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                -1.4, 1.4, 0.5,
                -1.4, 1.4, 0.65,
                -1.4, -1.4, 0.65
        );
        this.add(t);
    }

    @SuppressWarnings("MagicNumber")
    private void backRest() {
        // surface of the seat is z = 0.65
        // front/back
        t = MeshFactory.makeTriangle(
                -1.4, -1.4, 0.65,
                -1.4, -1.4, 1.25,
                1.4, -1.4, 1.25
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                -1.4, -1.4, 0.65,
                1.4, -1.4, 0.65,
                1.4, -1.4, 1.25
        );
        this.add(t);

        t = MeshFactory.makeTriangle(
                -1.4, -1.2, 0.65,
                -1.4, -1.2, 1.25,
                1.4, -1.2, 1.25
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                -1.4, -1.2, 0.65,
                1.4, -1.2, 0.65,
                1.4, -1.2, 1.25
        );
        this.add(t);
        
        // left/right
        t = MeshFactory.makeTriangle(
                -1.4, -1.4, 0.65,
                -1.4, -1.2, 0.65,
                -1.4, -1.4, 1.25
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                -1.4, -1.2, 1.25,
                -1.4, -1.2, 0.65,
                -1.4, -1.4, 1.25
        );
        this.add(t);

        t = MeshFactory.makeTriangle(
                1.4, -1.4, 0.65,
                1.4, -1.2, 0.65,
                1.4, -1.4, 1.25
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                1.4, -1.2, 1.25,
                1.4, -1.2, 0.65,
                1.4, -1.4, 1.25
        );
        this.add(t);

        // top
        t = MeshFactory.makeTriangle(
                -1.4, -1.4, 0.65,
                -1.4, -1.2, 0.65,
                1.4, -1.4, 0.65
        );
        this.add(t);
        t = MeshFactory.makeTriangle(
                1.4, -1.2, 0.65,
                -1.4, -1.2, 0.65,
                1.4, -1.4, 0.65
        );
        this.add(t);
    }
}
