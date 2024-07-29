package nl.elisabeth.adventofcodeinscala

object Day2_CodeConundrum {
  // a small bag and some cubes which are either red, green, or blue
  // Each time you play this game, he will hide a secret number of cubes of each color in the bag,
  // and your goal is to figure out information about the number of cubes.
  // Once a bag has been loaded with cubes, the Elf will reach into the bag, grab a handful of random cubes,
  // show them to you, and then put them back in the bag. He'll do this a few times per game.

  // Each line in the text file contains a game with the cubes the elf takes out.
  // e.g.: Game 1: 4 blue, 7 red, 5 green; 3 blue, 4 red, 16 green; 3 red, 11 green
  // Here, three sets of cubes are shown (in the last set, no blue stones are shown)

  // The Elf would first like to know which games would have been possible if the bag contained only 12 red cubes,
  // 13 green cubes, and 14 blue cubes?
  // Return the sum of the IDs of those games.


  // PLAN
  // eliminate all games that contain more than the given numbers of cubes.
    // red green and blue or not always in the same order
    // sometimes only some of the colours are shown
    // one game can have a variable number of hands
}
