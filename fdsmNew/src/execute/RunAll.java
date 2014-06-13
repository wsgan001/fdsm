package execute;

import algo.Cooccurrence;
import algo.Cosine;
import algo.Covariance;
import algo.HypergeometricCoefficient;
import algo.Jaccard;
import algo.LevFDSM;
import algo.PValue;
import algo.PearsonCorrelation;
import algo.PresortedZScore;
import algo.SLevFDSM;
import algo.Zscore;

public class RunAll {

	public static void main(String[] args) {
		
		Cooccurrence.run();
		System.out.println("Co-occurrence finished"+System.lineSeparator());
		
		Cosine.run();
		System.out.println("Cosine finished"+System.lineSeparator());
		
		Covariance.run();
		System.out.println("Covariance finished"+System.lineSeparator());
		
		HypergeometricCoefficient.run();
		System.out.println("Hypergeometric Coefficient finished"+System.lineSeparator());
		
		Jaccard.run();
		System.out.println("Jaccard finished"+System.lineSeparator());
		
		LevFDSM.run();
		System.out.println("LevFDSM finished"+System.lineSeparator());
		
		SLevFDSM.run();
		System.out.println("SLevFDSM finished"+System.lineSeparator());
		
		PearsonCorrelation.run();
		System.out.println("Pearson Correlation finished"+System.lineSeparator());
		
		PValue.run();
		System.out.println("PValue finished"+System.lineSeparator());
		
		Zscore.run();
		System.out.println("Zscore finished"+System.lineSeparator());
		
		PresortedZScore.run();
		System.out.println("PresortedZScore finished"+System.lineSeparator());
		
	}

}
