import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reglas',
  templateUrl: './reglas.component.html',
  styleUrls: ['./reglas.component.css']
})
export class ReglasComponent implements OnInit {
  title : string = 'Reglas';
  regla1 : string = 'Todos pueden ver la lista entidad';
  regla2 : string = 'Solo el administrador puede hacer cambios';
  usr1: string = 'Usuario: admin | PassWord:12345';
  usr2: string = 'Usuario: invitado | PassWord:12345';
  constructor() { }

  ngOnInit(): void {
  }

}
