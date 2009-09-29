/**
 * Copyright (C) 2003 - 2009
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science
 * University of Pretoria
 * South Africa
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
/**
 *
 */
package net.sourceforge.cilib.functions.continuous.unconstrained;

import net.sourceforge.cilib.functions.ContinuousFunction;
import net.sourceforge.cilib.type.types.container.Vector;

/**
 * <p><b>De Jong's Function 4 (no noise).</b></p>
 *
 * <p><b>Reference:</b> S. Rahnamayan, H. R. Tizhoosh, M. M. A. Salama <i>A novel population initialization method for accelerating evolutionary algorithms</i>,
 * Computers and Mathematics with Applications, 2007</p>
 *
 *
 * <p>Minimum:
 * <ul>
 * <li> &fnof;(<b>x</b>*) = 0</li>
 * <li> <b>x</b>* = (0, 0, ...., 0)</li>
 * <li> for x<sub>i</sub> in [-1.28, 1.28]</li>
 * </ul>
 * </p>
 *
 * <p>Characteristics:
 * <ul>
 * </ul>
 * </p>
 *
 * @author leo
 *
 */
public class DeJongF4 extends ContinuousFunction {

    private static final long serialVersionUID = 4835441178770462999L;

    public DeJongF4() {
        setDomain("R(-1.28, 1.28)^30");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeJongF4 getClone() {
        return new DeJongF4();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getMinimum() {
        return new Double(0.0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double evaluate(Vector x) {
        double sum = 0;
        for(int i = 0; i < x.size(); ++i){
            double xi = x.getReal(i);
            sum += (i+1) * (xi*xi*xi*xi);
        }
        return sum;
    }

}