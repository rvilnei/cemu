import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { GuiasService } from 'src/app/guias/guias.service';
import { Guia } from 'src/app/guias/guia';

@Component({
  selector: 'app-guias',
  templateUrl: './guias.component.html',
  styleUrls: ['./guias.component.css']
})
export class GuiasComponent implements OnInit {
  guias$: Observable< Guia[] >;
 // guiaEdit: Guia ;

  currentPage:number = 1;
  numPages:number = 0;
  itemsPerPage = 8;


  constructor(
    private guiasService: GuiasService 
  ) { }

  ngOnInit() {
    this.guias$ = this.guiasService.getGuias();
  }
    
  openNewWindowForJasperWithCharts(){
    var url  = "/localhost:8080/reports/1/pdf";
    var strWindowFeatures = "menubar=no,location=no,width=800,height=500";
    window.open(url,"_blank", "location=0,height=500,width=800");
  }

}
