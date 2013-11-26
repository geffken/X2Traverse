#include <stdio.h>

int main(int argc, char *argv[])
{
  int edge, end, i, start;

  if ((argc <= 2) || (strcmp(argv[1], "left") && strcmp(argv[1], "right"))) {
    printf("usage: %s left|right str ...\n", argv[0]);
    exit(1);
  }
  edge = ((argc - 2) / 2) + 1;
  if (!strcmp(argv[1], "left")) {
    start = 2;
    end = edge + 1;
  }
  else {
    start = edge + 1;
    end = argc;
  }
  for (i = start; i < end; i++) {
    printf("%s", argv[i]);
    if (i < end)
      printf(" ");
  }
  if (start < end)
    printf("\n");
}
