package com.company;

import java.util.Random;

public class Main {


	// write your code here
        public static int bossHealth = 600;
        public static int bossDamage = 50;
        public static String bossDefenceType;
        public static int[] heroesHealth = {260, 250, 270, 150};
        public static int[] heroesDamage = {20, 15, 10, 0};
        public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic"};
        public static int roundNumber;

        public static void main(String[] args) {
            printStatistics();

            while (!isGameFinished()) {
                round();
            }
        }

        public static void chooseBossDefence() {
            Random random = new Random();
            int randomIndex = random.nextInt(heroesAttackType.length);
            bossDefenceType = heroesAttackType[randomIndex];
            System.out.println("Boss choose " + bossDefenceType);
        }

        public static void round() {
            roundNumber++;
            chooseBossDefence();
            bossHits();
            heroesHit();
            medic();
            printStatistics();
        }

        public static void bossHits() {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] > 0 && bossHealth > 0) {
                    if (heroesHealth[i] - bossDamage < 0) {
                        heroesHealth[i] = 0;
                    } else {
                        heroesHealth[i] = heroesHealth[i] - bossDamage;
                    }
                }
            }
        }

        public static void heroesHit() {
            for (int i = 0; i < heroesDamage.length; i++) {
                if (heroesHealth[i] > 0 && bossHealth > 0) {
                    if (bossDefenceType == heroesAttackType[i]) {
                        Random random = new Random();
                        int coeff = random.nextInt(12); //0,1,2,3,4,5,6,7,8,9,10,11
                        if (bossHealth - heroesDamage[i] * coeff < 0) {
                            bossHealth = 0;
                        } else {
                            bossHealth = bossHealth - heroesDamage[i] * coeff;
                        }
                        System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                    } else {
                        if (bossHealth - heroesDamage[i] < 0) {
                            bossHealth = 0;
                        } else {
                            bossHealth = bossHealth - heroesDamage[i];
                        }
                    }
                }
            }
        }
public static void medic(){
    for (int i = 0; i < heroesHealth.length; i++) {
      if(i == 3) {
          continue;
          }
       if (heroesHealth[i] <= 100 && heroesHealth[i] > 0 && heroesHealth[3] > 0)

       {
           heroesHealth[i] = heroesHealth[i] + 50;
            System.out.println(" Медик излечил " + heroesAttackType[i]);
            break;
        }
      }
}



        public static boolean isGameFinished() {
            if (bossHealth <= 0) {
                System.out.println("Heroes won!!!");
                return true;
            }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
            boolean allHeroesDead = true;
            for (int i = 0; i <heroesHealth.length ; i++) {
                if(heroesHealth[i] > 0){
                    allHeroesDead = false;
                    break;
                }
            }
            if(allHeroesDead){
                System.out.println("Boss won!!!");
            }
            return allHeroesDead;
        }

        public static void printStatistics(){
            System.out.println(roundNumber + " ROUND -------------------");
            System.out.println("Boss health: " + bossHealth + " (" + bossDamage + ")");
            for (int i = 0; i < heroesHealth.length; i++) {
                System.out.println(heroesAttackType[i] +
                        " health: " + heroesHealth[i] + " (" + heroesDamage[i] + ")");
            }
        }
    }

