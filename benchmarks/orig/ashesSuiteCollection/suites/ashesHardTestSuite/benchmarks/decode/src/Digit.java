import java.util.Arrays;

public class Digit
{
  public Digit(int i)
  {
    _digit = i % MODULUS;
  }

  public Digit(char c)
  {
    _digit = Arrays.binarySearch(CHARACTER_MAP, c);
  }

  public int IntValue()
  {
    return _digit;
  }

  public char CharValue()
  {
    return CHARACTER_MAP[_digit];
  }

  public static char CharValue(int i)
  {
    return CHARACTER_MAP[i % MODULUS];
  }

  public String toString()
  {
    return (new Character(CHARACTER_MAP[_digit])).toString();
  }

  public Digit add(Digit rhs)
  {
    return new Digit((_digit + rhs._digit) % MODULUS);
  }

  public Digit sub(Digit rhs)
  {
    int tmp = _digit - rhs._digit;
    while (tmp < 0) tmp += MODULUS;
    return new Digit(tmp % MODULUS);
  }

  public Digit mult(Digit rhs)
  {
    return new Digit((_digit * rhs._digit) % MODULUS);
  }

  public Digit div(Digit rhs)
  {
    if (rhs._digit == 0)
      throw new RuntimeException("Digit: divide by 0");

    return new Digit((_digit * INVERSE_MAP[rhs._digit]) % MODULUS);
  }

  //-- member data -------------------------------------------------------------

  private int _digit;

  //-- constant data -----------------------------------------------------------

  public static final int MODULUS = 89;

  static final char CHARACTER_MAP[] =
  {
    ' ', '!', '\"', '#' ,'$', '%', '\'', '(', ')', '*', '+', ',', '-', '.',
    '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<',
    '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
    'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
    'Y', 'Z', '[', '\\', ']', '^', '_', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
    'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
    'v', 'w', 'x', 'y', 'z'
  };

  static final int INVERSE_MAP[] =
  {
    -1,
     1, 45, 30, 67, 18, 15, 51, 78, 10,  9, 81, 52, 48, 70,  6, 39, 21,  5, 75, 49,
    17, 85, 31, 26, 57, 24, 33, 35, 43,  3, 23, 64, 27, 55, 28, 47, 77, 82, 16, 69,
    76, 53, 29, 87,  2, 60, 36, 13, 20, 73,  7, 12, 42, 61, 34, 62, 25, 66, 86, 46,
    54, 56, 65, 32, 63, 58,  4, 72, 40, 14, 84, 68, 50, 83, 19, 41, 37,  8, 80, 79,
    11, 38, 74, 71, 22, 59, 44, 88
  };
}
