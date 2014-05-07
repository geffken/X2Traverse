;>> boolean.class <<
;
; Output created by D-Java (Jan 22 1998)
; mailto:umsilve1@cc.umanitoba.ca
; Copyright (c) 1996,1997 Shawn Silverman
;

;Classfile version:
;    Major: 45
;    Minor: 3


.class  public spread_illness/boolean
; ACC_SUPER bit is set
.super  spread_illness/_any

; >> METHOD 1 <<
.method public <init>()V
    .limit stack 1
    .limit locals 1
    aload_0
    invokenonvirtual spread_illness/_any/<init>()V
    return
.end method

; >> METHOD 2 <<
.method public static <clinit>()V
    .limit stack 0
    .limit locals 1
    return
.end method

; >> METHOD 3 <<
.method public static _px_and(ZZ)Z
    .limit stack 2
    .limit locals 3
    iconst_0
    istore_2
    iload_0
    ifeq Label1
    iload_1
    goto Label2
Label1:
    iconst_0
Label2:
    istore_2
    iload_2
    ireturn
.end method


; >> METHOD 4 -added by LJH  <<
.method public static _ix_not(Z)Z
    .limit stack 2
    .limit locals 3
    iload_0
    ifeq Label1
    iconst_0
    goto Label2
Label1:
    iconst_1
Label2:
    ireturn
.end method
