// src/pages/ApprovalsPage.tsx
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { ArrowLeft } from "lucide-react";
import { LogOut } from "lucide-react";
import { authService } from "@/services/authService";

const ApprovalsPage = () => {
  const navigate = useNavigate();

    const handleLogout = () => {
      authService.logout();
      navigate("/");
    };

    const handleBack = () => {
      const role = authService.getRole();
      if (role === "admin") navigate("/admin");
      else navigate("/staff");
    };

  const [items] = useState([
    { id: "P001", name: "Badrul", month: "2025-08", status: "Pending" },
    { id: "P002", name: "Azfar", month: "2025-08", status: "Pending" },
  ]);

  return (
    <div className="min-h-screen bg-background">
      {/* Header */}
      <header className="bg-surface border-b border-border px-6 py-4">
        <div className="flex items-center justify-between">
          <div className="flex items-center space-x-4">
            <Button variant="ghost" size="sm" onClick={handleBack}>
              <ArrowLeft className="h-5 w-5" />
            </Button>
            <div className="w-10 h-10 bg-gradient-primary rounded-lg flex items-center justify-center">
              <span className="text-lg font-bold text-primary-foreground">N</span>
            </div>
            <div>
              <h1 className="text-xl font-bold text-foreground">NOCTS</h1>
              <p className="text-sm text-muted-foreground">Approval Control</p>
            </div>
          </div>
          <Button variant="destructive" size="lg" onClick={handleLogout}>
            <LogOut className="mr-2 h-5 w-5" />
            Logout
          </Button>
        </div>
      </header>

      {items.map((it) => (
        <Card key={it.id} className="mb-4">
          <CardHeader>
            <CardTitle>
              {it.name} — {it.month}
            </CardTitle>
          </CardHeader>

          <CardContent className="flex items-center justify-between">
            <div>
              <p>ID: {it.id}</p>
              <p>Status: {it.status}</p>
            </div>

            <div className="flex gap-2">
              <Button onClick={() => alert(`Approved ${it.id}`)}>
                Approve
              </Button>

              <Button 
                variant="destructive" 
                onClick={() => alert(`Rejected ${it.id}`)}
              >
                Reject
              </Button>
            </div>
          </CardContent>
        </Card>
      ))}
    </div>
  );
};

export default ApprovalsPage;
