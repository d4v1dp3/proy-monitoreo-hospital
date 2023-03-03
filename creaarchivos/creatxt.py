import os

def buscaPorRuta(ruta, archivoTexto):
    with open(archivoTexto, 'w') as f: 
        for path, dirActual, archivos in os.walk(ruta):
            for archivo in archivos:
                print(os.path.join(path,archivo))
                f.write(os.path.join(path,archivo))
                f.write("\n")


if __name__ =="__main__":
    buscaPorRuta("./proy-monitoreo-hospital/sistmhospital-ejb","ejbFile.txt")
    buscaPorRuta("./proy-monitoreo-hospital/sistmhospital-web","webFile.txt")

