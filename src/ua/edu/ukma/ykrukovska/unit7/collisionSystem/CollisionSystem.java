package ua.edu.ukma.ykrukovska.unit7.collisionSystem;

import edu.princeton.cs.algs4.StdDraw;


public class CollisionSystem {
    // number of redraw events per clock tick
    private static final double HZ = 2.0;

    private MinPQ<Event> pq;
    private double t = 0.0;   // simulation clock time
    private Particle[] particles;


    public CollisionSystem(Particle[] particles) {
        this.particles = particles.clone();
    }

    private void predict(Particle a) {
        if (a == null) {
            return;
        }

        // particle collisions with each other
     /*   for (Particle particle : particles) {
            double dt = a.timeToHit(particle);
            pq.insert(new Event(t + dt, a, particle));
        }*/

        // particle-wall collisions
        double dtX = a.timeToHitVerticalWall();
        double dtY = a.timeToHitHorizontalWall();

        pq.insert(new Event(t + dtX, a, null));


        pq.insert(new Event(t + dtY, null, a));

    }

    private void redraw() {
        StdDraw.clear();
        for (Particle particle : particles) {
            particle.draw();
        }
        StdDraw.show();
        StdDraw.pause(20);

        pq.insert(new Event(t + 1.0 / HZ, null, null));

    }


    public void simulate() {

        pq = new MinPQ<>();
        for (Particle particle : particles) {
            predict(particle);
        }
        pq.insert(new Event(0, null, null));


        while (!pq.isEmpty()) {

            Event event = pq.delMin();
            if (!event.isValid()) continue;
            Particle a = event.a;
            Particle b = event.b;

            for (int i = 0; i < particles.length; i++)
                particles[i].move(event.time - t);
            t = event.time;

            if (a != null && b != null) {
                a.bounceOff(b);              // particle-particle collision
            } else if (a != null && b == null) {
                a.bounceOffVerticalWall();   // particle-wall collision
            } else if (a == null && b != null) {
                b.bounceOffHorizontalWall(); // particle-wall collision
            } else if (a == null && b == null) {
                redraw();               // redraw event
            }

            predict(a);
            predict(b);
        }
    }

    /* a & b == null - redraw
     *  a == null, b != null - collision with vertical wall
     *  a != null, b == null - collision with horizontal wall
     *  a & b != null - collision
     */

    private static class Event implements Comparable<Event> {
        private final double time;
        private final Particle a, b;
        private final int countA, countB;

        public Event(double t, Particle a, Particle b) {
            this.time = t;
            this.a = a;
            this.b = b;
            if (a != null) {
                countA = a.count();
            } else {
                countA = -1;
            }
            if (b != null) {
                countB = b.count();
            } else {
                countB = -1;
            }
        }

        public int compareTo(Event that) {
            return Double.compare(this.time, that.time);
        }

        public boolean isValid() {
            if (a != null && a.count() != countA) {
                return false;
            }
            if (b != null && b.count() != countB) {
                return false;
            }
            return true;
        }

    }


    public static void main(String[] args) {

        StdDraw.setCanvasSize(600, 600);

        StdDraw.enableDoubleBuffering();
        Particle[] particles;


        int n = 20;
        particles = new Particle[n];
        for (int i = 0; i < n; i++)
            particles[i] = new Particle();


        CollisionSystem system = new CollisionSystem(particles);
        system.simulate();
    }

}