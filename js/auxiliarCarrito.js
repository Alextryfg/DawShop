function decrementarCantidad(num) {
    var campoCantidad = document.getElementById("cantidad-" + num);
    var cantidad = parseInt(campoCantidad.value);
    cantidad -= 1;
    if (cantidad < 0) {
        cantidad = 0;
    }
    campoCantidad.value = cantidad;
    document.getElementsByName("itemCantidad[]")[num].value = cantidad;
}