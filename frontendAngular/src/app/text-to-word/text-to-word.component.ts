import { Component } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-text-to-word',
  templateUrl: './text-to-word.component.html',
  styleUrls: ['./text-to-word.component.css']
})
export class TextToWordComponent {
  userText: string = '';
  isLoading: boolean = false;
  errorMessage: string = '';
  successMessage: string = '';

  constructor(private http: HttpClient) {}

  generateWord() {
    if (!this.userText || this.userText.trim().length < 5) {
      this.errorMessage = 'Veuillez saisir au moins 5 caractères';
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';
    this.successMessage = '';
    
    const body = { text: this.userText };

    this.http.post('http://localhost:8080/api/word/generate', body, {
      responseType: 'blob',
      observe: 'response'
    }).subscribe({
      next: (response) => {
        if (response.body) {
          const file = new Blob([response.body], {
            type: 'application/vnd.openxmlformats-officedocument.wordprocessingml.document',
          });
          const url = window.URL.createObjectURL(file);
          const a = document.createElement('a');
          a.href = url;
          a.download = 'document_ameliore.docx';
          a.click();
          window.URL.revokeObjectURL(url);
          this.successMessage = 'Document généré avec succès!';
        }
        this.isLoading = false;
      },
      error: (err: HttpErrorResponse) => {
        this.isLoading = false;
        if (err.status === 400) {
          this.errorMessage = 'Erreur: Le texte doit contenir au moins 5 caractères';
        } else {
          this.errorMessage = 'Erreur lors de la génération du document';
        }
      }
    });
  }

  clearText() {
    this.userText = '';
    this.errorMessage = '';
    this.successMessage = '';
  }
}