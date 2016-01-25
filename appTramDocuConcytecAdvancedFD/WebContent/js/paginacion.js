function opcSeleccion(a) {
  if ( a == "next" ) {
    document.NavigFormNext.Action.value = a;
    document.NavigFormNext.submit();
  } else {
    document.NavigFormBack.Action.value = a;
    document.NavigFormBack.submit();
  }
}
