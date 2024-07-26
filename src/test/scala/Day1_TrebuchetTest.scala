import Day1_Trebuchet.*

class Day1_TrebuchetTest extends munit.FunSuite:

  test("firstDigitCombinedWithLastDigitInString '4hello2' should return an Option with value 42") {
    assertEquals(firstDigitCombinedWithLastDigitInString("4hello2"), Some(42))
  }

  test("firstDigitCombinedWitLastDigitInString '7' should return an Option with value 77") {
    assertEquals(firstDigitCombinedWithLastDigitInString("7"), Some(77))
  }

  test("trebuchet should return 54338") {
    val root = os.pwd
    val filePathTrebuchet: os.Path = root / "src" / "main" / "resources" / "trebuchet.txt"
    assertEquals(part1(filePathTrebuchet), 54338)
  }
