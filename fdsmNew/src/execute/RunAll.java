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
		Cosine.run();
		Covariance.run();
		HypergeometricCoefficient.run();
		Jaccard.run();
		LevFDSM.run();
		SLevFDSM.run();
		PearsonCorrelation.run();
		PValue.run();
		Zscore.run();
		PresortedZScore.run();
		
		

	}

}
