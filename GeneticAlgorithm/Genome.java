package GeneticAlgorithm;

class Genome implements Comparable<Genome> {

    int fitness;
    String str;

    public Genome(String str) {
        this.str = str;
        int fitness = 0;
        for (int j = 0; j < str.length(); j++) {
            fitness += Math.abs(str.charAt(j) - ConstParameters.LAST_GENERATION_GEN.charAt(j));
        }
        this.fitness = fitness;
    }

    public int compareTo(Genome o) {
        return fitness - o.fitness;
    }

    public String toString(){
        return fitness + " " + str;
    }
}
