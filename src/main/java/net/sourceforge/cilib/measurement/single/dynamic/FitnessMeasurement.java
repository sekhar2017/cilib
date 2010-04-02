/*
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
package net.sourceforge.cilib.measurement.single.dynamic;

import net.sourceforge.cilib.algorithm.Algorithm;
import net.sourceforge.cilib.measurement.Measurement;
import net.sourceforge.cilib.type.types.Real;
import net.sourceforge.cilib.type.types.Type;

/**
 * Calculate the actual fitness of the particle at their current position.
 * This measurement should be used on dynamic algorithms where the pbest of the
 * particles may be innacurate (outdated).
 *
 * @author Julien Duhain
 *
 */
public class FitnessMeasurement implements Measurement {

    private static final long serialVersionUID = 2632671785674388015L;

    @Override
    public String getDomain() {
        return "R";
    }

    @Override
    public Type getValue(Algorithm algorithm) {
        return Real.valueOf(algorithm.getOptimisationProblem().getFitness(algorithm.getBestSolution().getPosition()).getValue());
    }

    @Override
    public Measurement getClone() {
        return this;
    }
}
