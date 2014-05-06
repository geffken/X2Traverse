>> Main.class <<
;
; Output created by D-Java (Jan 22 1998)
; mailto:umsilve1@cc.umanitoba.ca
; Copyright (c) 1996,1997 Shawn Silverman
;

;Classfile version:
;    Major: 45
;    Minor: 3


.class  public Main
; ACC_SUPER bit is set
.super  java/lang/Object

; >> METHOD 1 <<
.method public static main([Ljava/lang/String;)V
    .limit stack 2031
    .limit locals 23
    aload_0
    getstatic G/a LF;
    astore_1
    getstatic G/b Ljava/io/BufferedOutputStream;
    astore_2
    getstatic G/c LF;
    astore_3
    getstatic G/d LF;
    astore 4
    getstatic G/e LRk;
    astore 5
    getstatic G/f LS;
    astore 6
    ifnonnull Label3
    aconst_null
    astore 8
Label1:
    aload 8
    ifnonnull Label17
    iconst_1
    istore 10
Label2:
    iload 10
    ifne Label18
    aload 8
    ifnonnull Label7
    new Ef
    dup
    invokenonvirtual Ef/<init>()V
    athrow
Label3:
    aload_0
    arraylength
    istore 7
    aconst_null
    astore 8
    goto Label5
Label4:
    new Rm
    dup
    aload 9
    aload 8
    invokenonvirtual Rm/<init>(Ljava/lang/String;LRm;)V
    astore 8
Label5:
    iload 7
    ifeq Label1
    aload_0
    iload 7
    iconst_m1
    iadd
    dup
    istore 7
    aaload
    dup
    astore 9
    ifnonnull Label4
    new Rm
    dup
    ldc ""
    aload 8
    invokenonvirtual Rm/<init>(Ljava/lang/String;LRm;)V
    astore 8
    goto Label5
Label6:
    aload_2
    ldc "\n"
    aload_2
    ldc "Proved!\n"
    invokestatic G/c(Ljava/io/BufferedOutputStream;Ljava/lang/String;)V
    invokestatic G/c(Ljava/io/BufferedOutputStream;Ljava/lang/String;)V
    return
Label7:
    new Ff
    dup
    aload 8
    getfield Rm/a Ljava/lang/String;
    dup
    astore 15
    invokevirtual java/lang/String/length()I
    istore 16
    invokenonvirtual Ff/<init>()V
    dup
    astore 12
    iconst_0
    aload 12
    iload 16
    putfield Ff/b I
    aload 12
    aload 15
    putfield Ff/a Ljava/lang/String;
    invokestatic G/a(LF;I)LRf;
    dup
    astore 13
    getfield Rf/b I
    dup
    istore 14
    iload 16
    aload 13
    getfield Rf/a I
    istore 17
    if_icmplt Label8
    aconst_null
    astore 18
    goto Label9
Label8:
    new Rg
    dup
    aload 15
    iload 14
    invokevirtual java/lang/String/charAt(I)C
    iload 14
    iconst_1
    iadd
    invokenonvirtual Rg/<init>(CI)V
    astore 18
Label9:
    aload 18
    ifnonnull Label10
    aconst_null
    astore 21
    goto Label11
Label10:
    aload 18
    getfield Rg/a C
    bipush 10
    invokestatic java/lang/Character/digit(CI)I
    dup
    istore 19
    aload 18
    getfield Rg/b I
    istore 20
    iflt Label16
    aload 15
    iload 16
    iload 17
    iload 20
    iload 19
    iconst_0
    invokestatic G/o(Ljava/lang/String;IIIII)LRf;
    astore 21
Label11:
    aload 21
    ifnonnull Label12
    aconst_null
    astore 22
    goto Label13
Label12:
    new Rn
    dup
    aload 21
    getfield Rf/a I
    invokenonvirtual Rn/<init>(I)V
    astore 22
Label13:
    aload 22
    ifnonnull Label14
    new Ed
    dup
    invokenonvirtual Ed/<init>()V
    athrow
Label14:
    aload 22
    getfield Rn/a I
    istore 11
Label15:
    aload_1
    aload_2
    aload_3
    aload 5
    aload 6
    aload_2
    ldc "The answer is: "
    aload_1
    aload_2
    aload_3
    aload 5
    aload 6
    iload 11
    iconst_m1
    iadd
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "get"
    new Ra
    dup
    new Sa
    dup
    bipush 9
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "quotient"
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "flatten"
    new Ra
    dup
    new Sc
    dup
    ldc "cdr"
    new Ra
    dup
    new Sc
    dup
    ldc "gother"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "car"
    new Ra
    dup
    new Sc
    dup
    ldc "gother"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "assignment"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "lt"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "last"
    new Ra
    dup
    new Sc
    dup
    ldc "append"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "nth"
    new Ra
    dup
    new Sc
    dup
    ldc "nil"
    aconst_null
    iconst_0
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "meaning"
    new Ra
    dup
    new Sc
    dup
    ldc "plus_tree"
    new Ra
    dup
    new Sc
    dup
    ldc "delete"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "difference"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "sigma"
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "quotient"
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "difference"
    new Ra
    dup
    new Sc
    dup
    ldc "add1"
    new Ra
    dup
    new Sc
    dup
    ldc "add1"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "length"
    new Ra
    dup
    new Sc
    dup
    ldc "cons"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "dsort"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "sort2"
    new Ra
    dup
    new Sc
    dup
    ldc "delete"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "lt"
    new Ra
    dup
    new Sc
    dup
    ldc "length"
    new Ra
    dup
    new Sc
    dup
    ldc "delete"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "remainder"
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "ge"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "prime_list"
    new Ra
    dup
    new Sc
    dup
    ldc "append"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "times_list"
    new Ra
    dup
    new Sc
    dup
    ldc "append"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "numberp"
    new Ra
    dup
    new Sc
    dup
    ldc "greatest_factor"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "greatest_factor"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "greatest_factor"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "samefringe"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "listp"
    new Ra
    dup
    new Sc
    dup
    ldc "gother"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "flatten"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "value"
    new Ra
    dup
    new Sc
    dup
    ldc "normalize"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "gcd"
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "lt"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "lt"
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "lt"
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "difference"
    new Ra
    dup
    new Sc
    dup
    ldc "add1"
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "difference"
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "remainder"
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "difference"
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "difference"
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "difference"
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "nth"
    new Ra
    dup
    new Sc
    dup
    ldc "append"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "gcd"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "power_eval"
    new Ra
    dup
    new Sc
    dup
    ldc "big_plus"
    new Ra
    dup
    new Sc
    dup
    ldc "power_rep"
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "power_eval"
    new Ra
    dup
    new Sc
    dup
    ldc "power_rep"
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "lt"
    new Ra
    dup
    new Sc
    dup
    ldc "remainder"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "lt"
    new Ra
    dup
    new Sc
    dup
    ldc "quotient"
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "remainder"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "lt"
    new Ra
    dup
    new Sc
    dup
    ldc "remainder"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "remainder"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "power_eval"
    new Ra
    dup
    new Sc
    dup
    ldc "big_plus"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "power_eval"
    new Ra
    dup
    new Sc
    dup
    ldc "big_plus"
    new Ra
    dup
    new Sa
    dup
    bipush 11
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sc
    dup
    ldc "remainder"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "append"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "count_list"
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "reverse_loop"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "reverse_loop"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "exp"
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "exp"
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "nth"
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "member"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "length"
    new Ra
    dup
    new Sc
    dup
    ldc "reverse"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "member"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "member"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "mc_flatten"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "exec"
    new Ra
    dup
    new Sc
    dup
    ldc "append"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "reverse"
    new Ra
    dup
    new Sc
    dup
    ldc "append"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "append"
    new Ra
    dup
    new Sc
    dup
    ldc "append"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "meaning"
    new Ra
    dup
    new Sc
    dup
    ldc "plus_tree"
    new Ra
    dup
    new Sc
    dup
    ldc "plus_fringe"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "meaning"
    new Ra
    dup
    new Sc
    dup
    ldc "plus_tree"
    new Ra
    dup
    new Sc
    dup
    ldc "append"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "difference"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "zerop"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "fix"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "implies"
    new Ra
    dup
    new Sa
    dup
    bipush 15
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "not"
    new Ra
    dup
    new Sa
    dup
    bipush 15
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "or"
    new Ra
    dup
    new Sa
    dup
    bipush 15
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "and"
    new Ra
    dup
    new Sa
    dup
    bipush 15
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "prime"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "falsify"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "tautology_checker"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "assume_false"
    new Ra
    dup
    new Sa
    dup
    bipush 21
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "assume_true"
    new Ra
    dup
    new Sa
    dup
    bipush 21
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "divides"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "reverse_"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "fact_"
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "countps_"
    new Ra
    dup
    new Sa
    dup
    bipush 11
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "even1"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "iff"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "boolean"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "ge"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "le"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "gt"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "eqp"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    aload_2
    aload 4
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "compile"
    new Ra
    dup
    new Sa
    dup
    iconst_5
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "reverse"
    new Ra
    dup
    new Sc
    dup
    ldc "codegen"
    new Ra
    dup
    new Sc
    dup
    ldc "optimize"
    new Ra
    dup
    new Sa
    dup
    iconst_5
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "nil"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "fix"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "fix"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "lt"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "ge"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "le"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "or"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "true"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "false"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "and"
    new Ra
    dup
    new Sc
    dup
    ldc "implies"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "implies"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sc
    dup
    ldc "zerop"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "true"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "odd"
    new Ra
    dup
    new Sc
    dup
    ldc "sub1"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 15
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "countps_loop"
    new Ra
    dup
    new Sa
    dup
    bipush 11
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 15
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "fact_loop"
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "one"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "reverse_loop"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "nil"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "zerop"
    new Ra
    dup
    new Sc
    dup
    ldc "remainder"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "cons"
    new Ra
    dup
    new Sc
    dup
    ldc "cons"
    new Ra
    dup
    new Sa
    dup
    bipush 21
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "true"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "cons"
    new Ra
    dup
    new Sc
    dup
    ldc "cons"
    new Ra
    dup
    new Sa
    dup
    bipush 21
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "false"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "tautologyp"
    new Ra
    dup
    new Sc
    dup
    ldc "normalize"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "nil"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "falsify1"
    new Ra
    dup
    new Sc
    dup
    ldc "normalize"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "nil"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "and"
    new Ra
    dup
    new Sc
    dup
    ldc "not"
    new Ra
    dup
    new Sc
    dup
    ldc "zerop"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "not"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "add1"
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "prime1"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "sub1"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 16
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sa
    dup
    bipush 15
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sa
    dup
    bipush 16
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "true"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "false"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "false"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 16
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sa
    dup
    bipush 15
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "true"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sa
    dup
    bipush 16
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "true"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "false"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "false"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sa
    dup
    bipush 15
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "false"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "true"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 16
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sa
    dup
    bipush 15
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sa
    dup
    bipush 16
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "true"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "false"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "true"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sc
    dup
    ldc "numberp"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_2
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    iconst_3
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_4
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_3
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_4
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sa
    dup
    iconst_2
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_3
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_4
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "or"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "not"
    new Ra
    dup
    new Sc
    dup
    ldc "numberp"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "and"
    new Ra
    dup
    new Sc
    dup
    ldc "zerop"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "zerop"
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_2
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "fix"
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "fix"
    new Ra
    dup
    new Sa
    dup
    iconst_2
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "difference"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "not"
    new Ra
    dup
    new Sc
    dup
    ldc "gt"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "difference"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "and"
    new Ra
    dup
    new Sc
    dup
    ldc "numberp"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "or"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "zerop"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sc
    dup
    ldc "meaning"
    new Ra
    dup
    new Sc
    dup
    ldc "plus_tree"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "meaning"
    new Ra
    dup
    new Sc
    dup
    ldc "plus_tree"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "fix"
    new Ra
    dup
    new Sc
    dup
    ldc "meaning"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "append"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "append"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "append"
    new Ra
    dup
    new Sc
    dup
    ldc "reverse"
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "reverse"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "or"
    new Ra
    dup
    new Sc
    dup
    ldc "zerop"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "zerop"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 15
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_4
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "exec"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "exec"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 15
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_4
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    iconst_4
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "append"
    new Ra
    dup
    new Sc
    dup
    ldc "flatten"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "append"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "or"
    new Ra
    dup
    new Sc
    dup
    ldc "member"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "member"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "reverse"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "member"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "length"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "intersect"
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_2
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "and"
    new Ra
    dup
    new Sc
    dup
    ldc "member"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "member"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_2
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    bipush 9
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 10
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sc
    dup
    ldc "exp"
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 9
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "exp"
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 10
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 9
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 10
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "exp"
    new Ra
    dup
    new Sc
    dup
    ldc "exp"
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 9
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 10
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "append"
    new Ra
    dup
    new Sc
    dup
    ldc "reverse"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "nil"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "reverse"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "sort_lp"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sc
    dup
    ldc "count_list"
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "count_list"
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "append"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_2
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_2
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "quotient"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "fix"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sc
    dup
    ldc "power_eval"
    new Ra
    dup
    new Sa
    dup
    bipush 11
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sc
    dup
    ldc "power_eval"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "power_eval"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "one"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "not"
    new Ra
    dup
    new Sc
    dup
    ldc "zerop"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 9
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "and"
    new Ra
    dup
    new Sc
    dup
    ldc "not"
    new Ra
    dup
    new Sc
    dup
    ldc "zerop"
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "or"
    new Ra
    dup
    new Sc
    dup
    ldc "zerop"
    new Ra
    dup
    new Sa
    dup
    bipush 9
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "not"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sa
    dup
    bipush 9
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "one"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "and"
    new Ra
    dup
    new Sc
    dup
    ldc "not"
    new Ra
    dup
    new Sc
    dup
    ldc "zerop"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "not"
    new Ra
    dup
    new Sc
    dup
    ldc "zerop"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "not"
    new Ra
    dup
    new Sc
    dup
    ldc "lt"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "fix"
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "power_rep"
    new Ra
    dup
    new Sa
    dup
    bipush 9
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 9
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "gcd"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "append"
    new Ra
    dup
    new Sc
    dup
    ldc "nth"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "nth"
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "difference"
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "length"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "fix"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "fix"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "difference"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "difference"
    new Ra
    dup
    new Sa
    dup
    iconst_2
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 22
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "difference"
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    iconst_2
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 22
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_2
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_2
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "add1"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "lt"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "and"
    new Ra
    dup
    new Sc
    dup
    ldc "not"
    new Ra
    dup
    new Sc
    dup
    ldc "zerop"
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "lt"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "not"
    new Ra
    dup
    new Sc
    dup
    ldc "zerop"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "gcd"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "value"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "cons"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "nil"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "and"
    new Ra
    dup
    new Sc
    dup
    ldc "nlistp"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "listp"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "flatten"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "flatten"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "and"
    new Ra
    dup
    new Sc
    dup
    ldc "or"
    new Ra
    dup
    new Sc
    dup
    ldc "zerop"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "one"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "one"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "one"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "not"
    new Ra
    dup
    new Sc
    dup
    ldc "and"
    new Ra
    dup
    new Sc
    dup
    ldc "or"
    new Ra
    dup
    new Sc
    dup
    ldc "zerop"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "one"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "not"
    new Ra
    dup
    new Sc
    dup
    ldc "numberp"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sc
    dup
    ldc "times_list"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "times_list"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "and"
    new Ra
    dup
    new Sc
    dup
    ldc "prime_list"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "prime_list"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 22
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "and"
    new Ra
    dup
    new Sc
    dup
    ldc "numberp"
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "or"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sa
    dup
    bipush 22
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "one"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "not"
    new Ra
    dup
    new Sc
    dup
    ldc "lt"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "or"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "and"
    new Ra
    dup
    new Sc
    dup
    ldc "numberp"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "one"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "one"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "and"
    new Ra
    dup
    new Sc
    dup
    ldc "not"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "not"
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "numberp"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "numberp"
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "sub1"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "sub1"
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 11
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "length"
    new Ra
    dup
    new Sa
    dup
    bipush 11
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "member"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 11
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 11
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "delete"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "sort2"
    new Ra
    dup
    new Sa
    dup
    bipush 11
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "sort2"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "cons"
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "cons"
    new Ra
    dup
    new Sa
    dup
    iconst_2
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "cons"
    new Ra
    dup
    new Sa
    dup
    iconst_3
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "cons"
    new Ra
    dup
    new Sa
    dup
    iconst_4
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "cons"
    new Ra
    dup
    new Sa
    dup
    iconst_5
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 6
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sc
    dup
    ldc "six"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "length"
    new Ra
    dup
    new Sa
    dup
    bipush 6
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "two"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "fix"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "two"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "quotient"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "two"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "quotient"
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "add1"
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "two"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "add1"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sc
    dup
    ldc "numberp"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "add1"
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "add1"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "difference"
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sc
    dup
    ldc "lt"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "not"
    new Ra
    dup
    new Sc
    dup
    ldc "lt"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sc
    dup
    ldc "lt"
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "not"
    new Ra
    dup
    new Sc
    dup
    ldc "lt"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "fix"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "fix"
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sc
    dup
    ldc "member"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "difference"
    new Ra
    dup
    new Sc
    dup
    ldc "meaning"
    new Ra
    dup
    new Sc
    dup
    ldc "plus_tree"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "meaning"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "meaning"
    new Ra
    dup
    new Sc
    dup
    ldc "plus_tree"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "add1"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sc
    dup
    ldc "numberp"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "plus"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "times"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "fix"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sc
    dup
    ldc "zerop"
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "nil"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sc
    dup
    ldc "listp"
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "last"
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sc
    dup
    ldc "listp"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "cons"
    new Ra
    dup
    new Sc
    dup
    ldc "car"
    new Ra
    dup
    new Sc
    dup
    ldc "last"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sc
    dup
    ldc "lt"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "true"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "equal"
    new Ra
    dup
    new Sc
    dup
    ldc "false"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 25
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "append"
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sc
    dup
    ldc "assignedp"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "assignment"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_0
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "assignment"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    iconst_1
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sc
    dup
    ldc "listp"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "car"
    new Ra
    dup
    new Sc
    dup
    ldc "flatten"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sc
    dup
    ldc "listp"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "cdr"
    new Ra
    dup
    new Sc
    dup
    ldc "flatten"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "cons"
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "nil"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sc
    dup
    ldc "zerop"
    new Ra
    dup
    new Sa
    dup
    bipush 24
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "zero"
    aconst_null
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "fix"
    new Ra
    dup
    new Sa
    dup
    bipush 23
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "set"
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 21
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 12
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sc
    dup
    ldc "if"
    new Ra
    dup
    new Sc
    dup
    ldc "eqp"
    new Ra
    dup
    new Sa
    dup
    bipush 9
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 8
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    new Ra
    dup
    new Sa
    dup
    bipush 21
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sc
    dup
    ldc "get"
    new Ra
    dup
    new Sa
    dup
    bipush 9
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    new Ra
    dup
    new Sa
    dup
    bipush 12
    iconst_1
    invokenonvirtual Sa/<init>(II)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    aconst_null
    invokenonvirtual Ra/<init>(LS;LRa;)V
    invokenonvirtual Ra/<init>(LS;LRa;)V
    iconst_0
    invokenonvirtual Sc/<init>(Ljava/lang/String;LRa;I)V
    invokevirtual F/c(LS;)LS;
    invokestatic G/e(Ljava/io/BufferedOutputStream;LS;)V
    invokestatic G/n(LF;Ljava/io/BufferedOutputStream;LF;LRk;LS;I)V
    invokestatic G/c(Ljava/io/BufferedOutputStream;Ljava/lang/String;)V
    invokestatic G/g(LRk;LS;)LS;
    invokevirtual F/c(LS;)LS;
    aconst_null
    aconst_null
    invokestatic G/m(LF;Ljava/io/BufferedOutputStream;LS;LRa;LRa;)I
    ifne Label6
    aload_2
    ldc "\n"
    aload_2
    ldc "Cannot prove!\n"
    invokestatic G/c(Ljava/io/BufferedOutputStream;Ljava/lang/String;)V
    invokestatic G/c(Ljava/io/BufferedOutputStream;Ljava/lang/String;)V
    return
Label16:
    aload 15
    iload 16
    iload 17
    iload 20
    iload 19
    iconst_1
    invokestatic G/o(Ljava/lang/String;IIIII)LRf;
    astore 21
    goto Label11
Label17:
    iconst_0
    istore 10
    goto Label2
Label18:
    iconst_1
    istore 11
    goto Label15
.end method
