>> native_arrayOcharacterF.class <<
;
; Output created by D-Java (Jan 22 1998)
; mailto:umsilve1@cc.umanitoba.ca
; Copyright (c) 1996,1997 Shawn Silverman
;

;Classfile version:
;    Major: 45
;    Minor: 3


.class  public spread_illness/native_arrayOcharacterF
; ACC_SUPER bit is set
.super  spread_illness/_any

; >> METHOD 1 <<
.method public <init>()V
    .limit stack 1
    .limit locals 2
    aload_0
    invokenonvirtual spread_illness/_any/<init>()V
    return
.end method

; >> METHOD 2 <<
.method public static <clinit>()V
    .limit stack 0
    .limit locals 2
    return
.end method

; >> METHOD 3 <<
.method public static copy_from([B[BI)V
    .limit stack 5
    .limit locals 4
    iconst_0
    istore_3
    iload_2
    istore_3
Label1:
    iload_3
    iconst_0
    if_icmplt Label2
    iconst_0
    goto Label3
Label2:
    iconst_1
Label3:
    ifne Label4
    aload_0
    iload_3
    aload_1
    iload_3
    baload
    bastore
    iload_3
    iconst_1
    isub
    istore_3
    goto Label1
Label4:
    return
.end method

; >> METHOD 4 <<
.method public static realloc([BII)[B
    .limit stack 4
    .limit locals 4
    aconst_null
    astore_3
    aload_0
    pop
    iload_2
    newarray byte
    checkcast [B
    astore_3
    aload_3
    aload_0
    iload_1
    iconst_1
    isub
    invokestatic spread_illness/native_arrayOcharacterF/copy_from([B[BI)V
    aload_3
    areturn
.end method

; >> METHOD 5 <<
.method public static fast_memcmp([B[BI)Z
    .limit stack 5
    .limit locals 5
    iconst_0
    istore 4
    iconst_0
    istore_3
    iconst_1
    istore 4
    iload_2
    iconst_1
    isub
    istore_3
Label1:
    iload_3
    iconst_0
    if_icmplt Label2
    iconst_0
    goto Label3
Label2:
    iconst_1
Label3:
    ifne Label4
    iload 4
    invokestatic spread_illness/boolean/_ix_not(Z)Z
    goto Label5
Label4:
    iconst_1
Label5:
    ifne Label8
    aload_0
    iload_3
    baload
    aload_1
    iload_3
    baload
    if_icmpeq Label6
    iconst_0
    goto Label7
Label6:
    iconst_1
Label7:
    istore 4
    iload_3
    iconst_1
    isub
    istore_3
    goto Label1
Label8:
    iload 4
    ireturn
.end method
