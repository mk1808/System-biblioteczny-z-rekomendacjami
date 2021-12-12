import { AfterViewInit, Component, OnInit } from '@angular/core';
import * as L from 'leaflet';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements OnInit, AfterViewInit {
  private map:any;
  constructor() { }

  ngOnInit(): void {
  }
  ngAfterViewInit(): void {
    this.initMap();
  }

  private initMap(): void {
   this.map = L.map('map', {
      center: [ 52.40044366325689, 16.91119412649224],
      zoom: 12
    });
    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18,
      minZoom: 3,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });
    L.marker([52.40044366325689, 16.91119412649224]).addTo(this.map );

    tiles.addTo(this.map);
  }
  
}
