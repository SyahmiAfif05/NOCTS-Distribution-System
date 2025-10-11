import { useState, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Card } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import { toast } from "sonner";
import { Scan, User, Fuel } from "lucide-react";

const BarcodeScan = () => {
  const [barcode, setBarcode] = useState("");
  const [userId, setUserId] = useState("");
  const [eligibility, setEligibility] = useState<"eligible" | "ineligible" | null>(null);
  const barcodeInputRef = useRef<HTMLInputElement>(null);
  const navigate = useNavigate();

  // Auto-focus barcode input on mount and after processing
  useEffect(() => {
    barcodeInputRef.current?.focus();
  }, []);

  // Handle barcode input
  const handleBarcodeChange = (value: string) => {
    setBarcode(value);
    
    // Auto-process when barcode is entered (assuming reasonable length)
    if (value.length >= 5) {
      processBarcode(value);
    }
  };

  const processBarcode = (code: string) => {
    setUserId(code);
    
    // Check eligibility based on first character
    const firstChar = code.charAt(0).toUpperCase();
    
    if (firstChar === "E") {
      setEligibility("eligible");
      toast.success("User verified - Eligible for subsidy", {
        description: `User ID: ${code}`,
      });
    } else if (firstChar === "I") {
      setEligibility("ineligible");
      toast.info("User verified - Not eligible for subsidy", {
        description: "Standard fuel pricing will apply",
        duration: 4000,
      });
    } else {
      setEligibility("ineligible");
      toast.error("Invalid user ID format", {
        description: "ID must start with 'E' (Eligible) or 'I' (Ineligible)",
        duration: 5000,
      });
    }
  };

  const handleContinue = () => {
    if (userId) {
      navigate("/fuel-activation", { state: { userId, eligibility } });
    }
  };

  const handleReset = () => {
    setBarcode("");
    setUserId("");
    setEligibility(null);
    setTimeout(() => barcodeInputRef.current?.focus(), 100);
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-background p-6">
      <Card className="w-full max-w-2xl p-12 shadow-kiosk-lg">
        <div className="space-y-8">
          {/* Header */}
          <div className="text-center space-y-4">
            <div className="flex justify-center">
              <div className="p-4 bg-primary/10 rounded-full">
                <Fuel className="h-16 w-16 text-primary" />
              </div>
            </div>
            <h1 className="text-4xl font-bold text-foreground">NOCTS Kiosk</h1>
            <p className="text-lg text-muted-foreground">Fuel Subsidy Distribution System</p>
          </div>

          {/* Barcode Input Section */}
          <div className="space-y-4">
            <div className="flex items-center gap-3 text-muted-foreground">
              <Scan className="h-6 w-6" />
              <label className="text-lg font-medium">Scan Barcode</label>
            </div>
            
            <Input
              ref={barcodeInputRef}
              type="text"
              value={barcode}
              onChange={(e) => handleBarcodeChange(e.target.value)}
              placeholder="Place barcode under scanner..."
              className="h-16 text-xl text-center font-mono tracking-wider"
              autoComplete="off"
              autoFocus
            />
          </div>

          {/* User ID Display */}
          {userId && (
            <div className="space-y-4 p-6 bg-secondary/50 rounded-lg">
              <div className="flex items-center gap-3 text-foreground">
                <User className="h-6 w-6" />
                <label className="text-lg font-medium">User ID</label>
              </div>
              
              <div className="text-2xl font-mono font-bold text-center py-2">
                {userId}
              </div>

              {/* Eligibility Status */}
              <div className="flex justify-center pt-2">
                {eligibility === "eligible" ? (
                  <Badge className="px-6 py-3 text-lg bg-success text-success-foreground">
                    ✓ Eligible for Subsidy
                  </Badge>
                ) : (
                  <Badge className="px-6 py-3 text-lg bg-destructive text-destructive-foreground">
                    ✗ Not Eligible
                  </Badge>
                )}
              </div>
            </div>
          )}

          {/* Action Buttons */}
          <div className="flex gap-4 pt-4">
            {userId ? (
              <>
                <Button
                  onClick={handleReset}
                  variant="outline"
                  size="lg"
                  className="flex-1 h-16 text-lg"
                >
                  Scan Another
                </Button>
                <Button
                  onClick={handleContinue}
                  size="lg"
                  className="flex-1 h-16 text-lg"
                >
                  Continue →
                </Button>
              </>
            ) : null}
          </div>
        </div>
      </Card>
    </div>
  );
};

export default BarcodeScan;
