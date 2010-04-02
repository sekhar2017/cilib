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
package net.sourceforge.cilib.coevolution.cooperative.problemdistribution;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import net.sourceforge.cilib.algorithm.population.PopulationBasedAlgorithm;
import net.sourceforge.cilib.coevolution.cooperative.problem.CooperativeCoevolutionProblemAdapter;
import net.sourceforge.cilib.math.random.generator.SeedSelectionStrategy;
import net.sourceforge.cilib.math.random.generator.Seeder;
import net.sourceforge.cilib.math.random.generator.ZeroSeederStrategy;
import net.sourceforge.cilib.problem.OptimisationProblem;
import net.sourceforge.cilib.pso.PSO;
import net.sourceforge.cilib.type.DomainRegistry;
import net.sourceforge.cilib.type.StringBasedDomainRegistry;
import net.sourceforge.cilib.type.types.Bounds;
import net.sourceforge.cilib.type.types.Real;
import net.sourceforge.cilib.type.types.container.Vector;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class RandomGroupingProblemDistributionTest {
    @Test
    public void RandomGroupingTest(){
        SeedSelectionStrategy seedStrategy = Seeder.getSeederStrategy();
        Seeder.setSeederStrategy(new ZeroSeederStrategy());
        try {
            final DomainRegistry problemDomain = new StringBasedDomainRegistry();
            problemDomain.setDomainString("R(0.0, 4.0)^5");
            Bounds bounds = new Bounds(0.0, 4.0);
            Vector data = new Vector();
            data.add(Real.valueOf(0.0, bounds));
            data.add(Real.valueOf(0.0, bounds));
            data.add(Real.valueOf(0.0, bounds));
            data.add(Real.valueOf(0.0, bounds));
            data.add(Real.valueOf(0.0, bounds));

            List<PopulationBasedAlgorithm> populations = Arrays.asList((PopulationBasedAlgorithm)new PSO(), (PopulationBasedAlgorithm)new PSO());

            Mockery context = new Mockery();
            final OptimisationProblem problem = context.mock(OptimisationProblem.class);
            context.checking(new Expectations() {{
                allowing (problem).getDomain();
                will(returnValue(problemDomain));
            }});

            RandomGroupingDistributionStrategy test = new RandomGroupingDistributionStrategy();
            test.performDistribution(populations, problem, data);

            CooperativeCoevolutionProblemAdapter p1 = (CooperativeCoevolutionProblemAdapter)populations.get(0).getOptimisationProblem();
            CooperativeCoevolutionProblemAdapter p2 = (CooperativeCoevolutionProblemAdapter)populations.get(1).getOptimisationProblem();

            assertEquals(3, p1.getDomain().getDimension(), 0.0);
            assertEquals(2, p2.getDomain().getDimension(), 0.0);

            assertEquals(1, p1.getProblemAllocation().getProblemIndex(0), 0.0);
            assertEquals(0, p1.getProblemAllocation().getProblemIndex(1), 0.0);
            assertEquals(3, p1.getProblemAllocation().getProblemIndex(2), 0.0);

            assertEquals(4, p2.getProblemAllocation().getProblemIndex(0), 0.0);
            assertEquals(2, p2.getProblemAllocation().getProblemIndex(1), 0.0);
        }
        finally {
            Seeder.setSeederStrategy(seedStrategy);
        }
    }
}
