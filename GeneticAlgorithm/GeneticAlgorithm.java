package GeneticAlgorithm;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class GeneticAlgorithm {

    public void start() {    

        List<Genome> chromosome = new ArrayList<>();
        initializePopulation(chromosome);     

        for (int i = 0; i< ConstParameters.MAX_GENERATION; i++) {
            Collections.sort(chromosome);
            System.out.println(i + " : " + chromosome.get(0));

            if (chromosome.get(0).fitness == 0) {
                break;
            }

            chromosome = crossover(chromosome);
        }     
    }

    private void initializePopulation(List<Genome> chromosome) {
        int lastGenLen = ConstParameters.LAST_GENERATION_GEN.length();

        for (int i = 0; i < ConstParameters.POPULATION_COUNT; i++) {
            StringBuilder str = new StringBuilder();
            for (int j = 0; j < lastGenLen; j++) {
                str.append((char) (Math.random() * 255));
            }
            Genome gen = new Genome(str.toString());
            chromosome.add(gen);
        }
    }
  
    private List<Genome> crossover(List<Genome> chromosome) {
        int esize = (int) (ConstParameters.POPULATION_COUNT * ConstParameters.ELITE_RATE);
        int lastGenLen = ConstParameters.LAST_GENERATION_GEN.length();
        List<Genome> children = new ArrayList<>();

        selectElite(chromosome, children, esize);

        for (int i = esize; i < ConstParameters.POPULATION_COUNT; i++) {
            int i1 = (int) (Math.random() * ConstParameters.POPULATION_COUNT * ConstParameters.SURVIVE_RATE);
            int i2 = (int) (Math.random() * ConstParameters.POPULATION_COUNT * ConstParameters.SURVIVE_RATE);
            int spos = (int) (Math.random() * lastGenLen);

            String str = chromosome.get(i1).str.substring(0, spos) +
                    chromosome.get(i2).str.substring(spos);

            if (Math.random() < ConstParameters.MUTATION_RATE) {
                str = mutate(str);
            }

            Genome child = new Genome(str);
            children.add(child);
        }

        return children;
    }

    private void selectElite(List<Genome> chromosome, List<Genome> children, int esize) {
        for(int i = 0; i < esize; i++){
            children.add(chromosome.get(i));
        }
    }

    private String mutate(String str) {
        int lastGenLen = ConstParameters.LAST_GENERATION_GEN.length();
        int iterPos = (int) (Math.random() * lastGenLen);
        char mutateValue = (char) (Math.random() * 255);

        return str.substring(0, iterPos) + mutateValue + str.substring(iterPos + 1);
    }
}
