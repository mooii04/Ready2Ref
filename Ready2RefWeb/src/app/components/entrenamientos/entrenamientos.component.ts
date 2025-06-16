import { Component } from '@angular/core';
import { HttpClient, HttpEventType } from '@angular/common/http';

@Component({
  selector: 'app-entrenamientos',
  templateUrl: './entrenamientos.component.html',
  styleUrls: ['./entrenamientos.component.css']
})
export class EntrenamientosComponent {
  selectedFile: File | null = null;
  uploadProgress: number = 0;
  error: string | null = null;
  success: string | null = null;
  fileResponse: any = null;
  loading = false;

  constructor(private http: HttpClient) {}

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0] || null;
    this.error = null;
    this.success = null;
    this.fileResponse = null;
  }

  onUpload() {
    if (!this.selectedFile) {
      this.error = 'Selecciona un archivo para subir.';
      return;
    }
    this.loading = true;
    this.error = null;
    this.success = null;
    this.fileResponse = null;

    const formData = new FormData();
    formData.append('file', this.selectedFile);

    this.http.post<any>('http://localhost:8080/upload', formData, {
      reportProgress: true,
      observe: 'events'
    }).subscribe({
      next: (event) => {
        if (event.type === HttpEventType.UploadProgress && event.total) {
          this.uploadProgress = Math.round(100 * event.loaded / event.total);
        } else if (event.type === HttpEventType.Response) {
          this.success = 'Archivo subido correctamente';
          this.fileResponse = event.body;
          this.loading = false;
        }
      },
      error: (err) => {
        this.error = err?.error?.message || 'Error al subir el archivo';
        this.loading = false;
      }
    });
  }
}
