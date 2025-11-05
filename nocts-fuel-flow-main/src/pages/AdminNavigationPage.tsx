// src/pages/AdminNavigationPage.tsx

import { useNavigate } from "react-router-dom";
import { Button } from "@/components/ui/button";
import { Card, CardContent } from "@/components/ui/card";
import { LogOut, BarChart3, CheckCircle } from "lucide-react";
import { authService } from "@/services/authService";

const AdminNavigationPage = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    authService.logout();
    navigate("/");
  };

  const handleMonthlyReport = () => {
    navigate("/monthly-report");
  };

  const handleApprovalPage = () => {
    navigate("/approvals");
  };

  return (
    <div className="min-h-screen bg-background">
      <header className="bg-surface border-b border-border px-6 py-4">
        <div className="flex items-center justify-between">
          <h1 className="text-2xl font-bold text-foreground">Admin Dashboard</h1>
          <Button variant="destructive" size="lg" onClick={handleLogout}>
            <LogOut className="mr-2 h-5 w-5" />
            Log Out
          </Button>
        </div>
      </header>


      {/* Main Content */}
      <main className="flex-1 flex items-center justify-center p-6">
        <div className="w-full max-w-2xl space-y-6">

          {/* View Monthly Report */}
          <Card className="bg-surface-elevated border-border hover:bg-surface cursor-pointer transition hover:shadow-lg"
            onClick={handleMonthlyReport}
          >
            <CardContent className="p-8 flex items-center space-x-6">
              <BarChart3 className="w-10 h-10 text-info" />
              <div>
                <h3 className="text-xl font-semibold text-foreground">View Monthly Report</h3>
                <p className="text-muted-foreground mt-1">Access sales analytics</p>
              </div>
            </CardContent>
          </Card>

          {/* Approve / Reject Submissions */}
          <Card className="bg-surface-elevated border-border hover:bg-surface cursor-pointer transition hover:shadow-lg"
            onClick={handleApprovalPage}
          >
            <CardContent className="p-8 flex items-center space-x-6">
              <CheckCircle className="w-10 h-10 text-primary" />
              <div>
                <h3 className="text-xl font-semibold text-foreground">Approve / Reject Payslips</h3>
                <p className="text-muted-foreground mt-1">Review staff payslip eligibility submissions</p>
              </div>
            </CardContent>
          </Card>

        </div>
      </main>
    </div>
  );
};

export default AdminNavigationPage;

