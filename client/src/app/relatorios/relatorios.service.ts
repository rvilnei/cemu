import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class RelatoriosService {
  private readonly API =`http://localhost:8080/`;

  constructor(
    private http: HttpClient
  ) { }

  imprimeGuia(id: number): any {
    const url = `${this.API}reports/${id}/pdf`;
    const fileName = "report.pdf";
    this.http.get( url, { responseType: 'blob' })
      .subscribe((blob: Blob) => {
        if (navigator.msSaveBlob) {
          // IE 10+
          navigator.msSaveBlob(blob, fileName);
        }
        else {
          let link = document.createElement("a");
          if (link.download !== undefined) {
            let url = URL.createObjectURL(blob);
            link.setAttribute("href", url);
            // link.setAttribute("download", fileName);
            link.setAttribute("target", "_blank");
            link.style.visibility = 'hidden';
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
          }
          else {
            //html5 download not supported
          }
        }   
});

  }

}
