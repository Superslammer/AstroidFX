package dk.sdu.cbse.common.bullet;


import dk.sdu.cbse.common.data.Entity;

public interface IBulletSPI {
    /**
     * Creates a bullet pointing in the angle of shooter. The bullet has it’s velocity pointing in the same way.
     * <p><b>Pre-conditions:</b></p>
     * <ul>
     *     <li>{@code entity} not null.</li>
     * </ul>
     *
     * <p><b>Post-conditions:</b></p>
     * <ul>
     *     <li>Returns a new {@code Bullet} object.</li>
     * </ul>
     *
     * @param shooter Is the entity who shot the bullet.
     */
    Bullet createBullet(Entity shooter);
}
